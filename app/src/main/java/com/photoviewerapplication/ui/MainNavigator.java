package com.photoviewerapplication.ui;

import com.photoviewerapplication.ui.MainActivity;
import com.photoviewerapplication.ui.MainContract;

public class MainNavigator implements MainContract.Navigator {
    private MainActivity activity;
    public MainNavigator(MainActivity activity) {
        this.activity = activity;
    }
}
