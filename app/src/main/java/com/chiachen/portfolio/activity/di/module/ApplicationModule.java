package com.chiachen.portfolio.activity.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jianjiacheng on 25/04/2018.
 */

@Module
public class ApplicationModule {

    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }


    @Provides
    public ModuleA provideModuleA(ModuleB moduleB) {
        return new ModuleA(moduleB);
    }

    @Provides
    public ModuleB provideModuleB(ModuleC moduleC, ModuleD moduleD) {
        return new ModuleB(moduleC, moduleD);
    }

    @Provides
    public ModuleC provideModuleC(ModuleE moduleE) {
        return new ModuleC(moduleE);
    }

    @Provides
    public ModuleD provideModuleD() {
        return new ModuleD();
    }

    @Provides
    public ModuleE provideModuleE() {
        return new ModuleE();
    }
}
