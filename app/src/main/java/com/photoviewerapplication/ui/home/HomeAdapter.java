package com.photoviewerapplication.ui.home;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import com.photoviewerapplication.R;

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {
    private List<Bitmap> mPhotoList;

    public HomeAdapter(List<Bitmap> photoList) {
        this.mPhotoList = photoList;
    }

    public void refreshList(List<Bitmap> photoList) {
        this.mPhotoList = photoList;

        notifyDataSetChanged();
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new HomeViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final HomeViewHolder holder, int position) {
        holder.mImage.setImageBitmap(mPhotoList.get(position));
    }

    @Override
    public int getItemCount() {
        return mPhotoList.size();
    }
}

