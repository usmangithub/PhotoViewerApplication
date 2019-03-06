package com.photoviewerapplication.ui.home;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.photoviewerapplication.BaseFragment;
import com.photoviewerapplication.BasePresenter;
import com.photoviewerapplication.R;
import com.photoviewerapplication.ui.home.di.HomeComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Completable;

public class HomeFragment extends BaseFragment implements HomeContract.View {
    public static final String TAG = "HomeFragment";

    private HomeComponent homeComponent;
    private Unbinder unbinder;
    private HomeAdapter mHomeAdapter;
    private ProgressDialog progressDialog;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.search_view)
    SearchView searchView;

    @BindView(R.id.emptyView)
    TextView emptyView;

    @Inject
    HomePresenter presenter;

    protected void inject(HomeComponent menuComponent) {
        menuComponent.inject(this);
    }

    public HomeComponent getCategoryComponent() {
        if (homeComponent == null) {
            homeComponent = HomeComponent.Initializer
                    .init(this, getTestApplication().getApplicationComponent());
        }
        return homeComponent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(getCategoryComponent());
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().setTitle("Photo Viewer");
        unbinder = ButterKnife.bind(this, view);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        mHomeAdapter = new HomeAdapter(new ArrayList<>());
        recyclerView.setAdapter(mHomeAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                presenter.doResearch(s);
                return false;
            }
        });
        presenter.init();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setBitmapList(List<Bitmap> bitmapList) {
        mHomeAdapter.refreshList(bitmapList);
        if (!bitmapList.isEmpty()) {
            emptyView.setVisibility(View.GONE);
        }
    }

    @Override
    public Completable showLoadingAnimation() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading photo...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        progressDialog.show();
        return Completable.complete();
    }

    @Override
    public Completable hideLoadingAnimation() {
        if(progressDialog.isShowing())
            progressDialog.dismiss();
        return Completable.complete();
    }
}

