package com.chiachen.portfolio.global;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by jianjiacheng on 15/03/2018.
 */

public class BaseApplication extends Application {

    public static BaseApplication sInstance;
    private static BaseGraph sDemoGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        buildComponentAndInject();

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }

    public void buildComponentAndInject() {
        sDemoGraph = BaseComponent.Initializer.init(sInstance);
    }

    public static BaseGraph component() {
        return sDemoGraph;
    }
}
