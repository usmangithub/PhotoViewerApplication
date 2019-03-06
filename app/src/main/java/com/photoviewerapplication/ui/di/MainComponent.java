package com.photoviewerapplication.ui.di;

import com.photoviewerapplication.dagger.activity.ActivityModule;
import com.photoviewerapplication.dagger.activity.ActivityScope;
import com.photoviewerapplication.dagger.application.ApplicationComponent;
import com.photoviewerapplication.ui.MainActivity;
import com.photoviewerapplication.ui.MainNavigator;
import com.photoviewerapplication.ui.MainPresenter;
import dagger.Component;

@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                com.photoviewerapplication.ui.di.MainModule.class
        }
)
public interface MainComponent extends ActivityModule.Exposes {

    void inject(MainActivity activity);

    void inject(MainPresenter presenter);

    void inject(MainNavigator navigator);

    final class Initializer {

        private Initializer() {
        }

        public static MainComponent init(final MainActivity mainActivity,
                                         final ApplicationComponent applicationComponent) {
            return DaggerMainComponent.builder()
                    .applicationComponent(applicationComponent)
                    .mainModule(new MainModule(mainActivity))
                    .build();
        }
    }
}
