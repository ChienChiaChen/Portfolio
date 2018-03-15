package com.chiachen.portfolio.global;

import android.app.Application;

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
    }

    public void buildComponentAndInject() {
        sDemoGraph = BaseComponent.Initializer.init(sInstance);
    }

    public static BaseGraph component() {
        return sDemoGraph;
    }
}
