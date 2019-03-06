package com.photoviewerapplication.dagger;

import com.photoviewerapplication.TestApplication;
import com.photoviewerapplication.dagger.application.ApplicationComponent;

public final class ComponentFactory {
    private ComponentFactory() {
    }

    public static ApplicationComponent createApplicationComponent(
            final TestApplication testApplication) {
        return ApplicationComponent.Initializer.init(testApplication);
    }
}
