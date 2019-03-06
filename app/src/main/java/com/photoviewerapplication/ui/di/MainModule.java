package com.photoviewerapplication.ui.di;

import com.photoviewerapplication.dagger.activity.ActivityModule;
import com.photoviewerapplication.dagger.activity.ActivityScope;
import com.photoviewerapplication.ui.MainActivity;
import com.photoviewerapplication.ui.MainNavigator;
import com.photoviewerapplication.ui.MainPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class MainModule extends ActivityModule {

    private MainActivity mainActivity;

    MainModule(final MainActivity mainActivity) {
        super(mainActivity);
        this.mainActivity = mainActivity;
    }

    @Provides
    @ActivityScope
    MainPresenter provideMainPresenter() {
        final MainPresenter mainPresenter =
                new MainPresenter(mainActivity,
                        new MainNavigator(mainActivity));
        mainActivity.getMainComponent().inject(mainPresenter);
        return mainPresenter;
    }
}
