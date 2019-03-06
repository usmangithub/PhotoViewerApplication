package com.photoviewerapplication.ui.home.di;

import com.photoviewerapplication.dagger.fragment.FragmentModule;
import com.photoviewerapplication.dagger.fragment.FragmentScope;
import com.photoviewerapplication.ui.home.HomeFragment;
import com.photoviewerapplication.ui.home.HomeNavigator;
import com.photoviewerapplication.ui.home.HomePresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule extends FragmentModule {

    private HomeFragment homeFragment;

    HomeModule(final HomeFragment homeFragment) {
        super(homeFragment);
        this.homeFragment = homeFragment;
    }

    @Provides
    @FragmentScope
    HomePresenter provideHomePresenter() {
        final HomePresenter homePresenter =
                new HomePresenter(homeFragment,
                        new HomeNavigator(homeFragment));
        homeFragment.getCategoryComponent().inject(homePresenter);
        return homePresenter;
    }
}
