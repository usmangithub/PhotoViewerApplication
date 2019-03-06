package com.photoviewerapplication.dagger.application.module;

import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import com.photoviewerapplication.TestApplication;
import com.photoviewerapplication.api.ApiManager;
import com.photoviewerapplication.api.ApiManagerImpl;
import com.photoviewerapplication.dagger.application.ForApplication;
import com.photoviewerapplication.shared_preference.SharedPreferenceManager;
import com.photoviewerapplication.shared_preference.SharedPreferenceManagerImpl;
import dagger.Module;
import dagger.Provides;

@Module
public final class ApplicationModule {

    private final TestApplication testApplication;

    public ApplicationModule(final TestApplication testApplication) {
        this.testApplication = testApplication;
    }

    @Provides
    @Singleton
    TestApplication provideTestApplication() {
        return testApplication;
    }

    @Provides
    @Singleton
    @ForApplication
    Context provideContext() {
        return testApplication;
    }

    @Provides
    @Singleton
    Resources provideResources() {
        return testApplication.getResources();
    }

    @Provides
    @Singleton
    SharedPreferenceManager provideSharedPreferenceManager(final @ForApplication Context context) {
        return new SharedPreferenceManagerImpl(context);
    }

    @Provides
    @Singleton
    ApiManager provideApiManager(final @ForApplication Context context) {
        return new ApiManagerImpl(context);
    }

    public interface Exposes {
        TestApplication testApplication();
        @ForApplication
        Context context();
        Resources resources();
        ApiManager ApiManager();
        SharedPreferenceManager SharedPreferenceManager();
    }
}
