package com.photoviewerapplication.ui.home;


import com.photoviewerapplication.ui.home.HomeContract;
import com.photoviewerapplication.ui.home.HomeFragment;

public class HomeNavigator implements HomeContract.Navigator {
    private HomeFragment fragment;
    public HomeNavigator(HomeFragment fragment) {
        this.fragment = fragment;
    }

}
