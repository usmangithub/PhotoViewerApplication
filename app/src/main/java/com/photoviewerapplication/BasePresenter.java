package com.photoviewerapplication;

import android.support.annotation.CallSuper;

import com.photoviewerapplication.BaseNavigator;

public abstract class BasePresenter <ViewT extends com.photoviewerapplication.BaseView, NavigatorT extends BaseNavigator>{
    protected ViewT view;
    protected NavigatorT navigator;

    protected BasePresenter(final ViewT view, final NavigatorT navigator) {
        this.view = view;
        this.navigator = navigator;
    }

    @CallSuper
    public void init() {
    }

    @CallSuper
    public void activate() {
    }

    @CallSuper
    public void deactivate() {
    }

    @CallSuper
    public void destroy() {
        view = null;
    }
}
