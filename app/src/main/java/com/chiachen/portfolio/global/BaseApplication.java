package com.chiachen.portfolio.global;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.chiachen.portfolio.activity.di.component.Dagger_BaseComponent;
import com.chiachen.portfolio.activity.di.component._BaseComponent;
import com.chiachen.portfolio.activity.di.module.BaseModule;
import com.facebook.stetho.Stetho;

/**
 * Created by jianjiacheng on 15/03/2018.
 */

public class BaseApplication extends Application {

    public static BaseApplication sInstance;
    private static BaseGraph sDemoGraph;

    private _BaseComponent baseComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        buildComponentAndInject();
        initResource();
        initBaseComponent();
        initStetho();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    private void initResource() {
        ResourceService.init(getApplicationContext());
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }

    private void initBaseComponent() {
        baseComponent = Dagger_BaseComponent.builder().baseModule(new BaseModule()).build();
    }

    public void buildComponentAndInject() {
        sDemoGraph = BaseComponent.Initializer.init(sInstance);
    }

    public static BaseGraph component() {
        return sDemoGraph;
    }

    public _BaseComponent getBaseComponent() {
        return baseComponent;
    }
}
