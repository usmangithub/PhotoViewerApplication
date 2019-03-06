package com.photoviewerapplication.ui.home.di;

import com.photoviewerapplication.dagger.application.ApplicationComponent;
import com.photoviewerapplication.dagger.fragment.FragmentModule;
import com.photoviewerapplication.dagger.fragment.FragmentScope;
import com.photoviewerapplication.ui.home.HomeFragment;
import com.photoviewerapplication.ui.home.HomeNavigator;
import com.photoviewerapplication.ui.home.HomePresenter;
import dagger.Component;

@FragmentScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                HomeModule.class
        }
)
public interface HomeComponent extends FragmentModule.Exposes {

    void inject(HomeFragment fragment);

    void inject(HomePresenter presenter);

    void inject(HomeNavigator navigator);

    final class Initializer {

        private Initializer() {
        }

        public static HomeComponent init(final HomeFragment homeFragment,
                                         final ApplicationComponent applicationComponent) {
            return DaggerHomeComponent.builder()
                    .applicationComponent(applicationComponent)
                    .homeModule(new HomeModule(homeFragment))
                    .build();
        }
    }
}
