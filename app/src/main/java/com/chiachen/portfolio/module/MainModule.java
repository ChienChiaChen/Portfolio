package com.chiachen.portfolio.module;

import android.app.Application;
import android.content.res.Resources;

import com.chiachen.portfolio.global.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    private final BaseApplication mApp;

    public MainModule(BaseApplication application) {
        mApp = application;
    }

    @Provides
    @Singleton
    protected Application provideApplication() {
        return mApp;
    }

    @Provides
    @Singleton
    protected Resources provideResources() {
        return mApp.getResources();
    }
}