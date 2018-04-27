package com.chiachen.portfolio.activity.di.module;

import javax.inject.Inject;

/**
 * Created by jianjiacheng on 25/04/2018.
 */

public class ModuleA {
    private ModuleB mModuleB;

    @Inject
    public ModuleA(ModuleB moduleB) {
        mModuleB = moduleB;
    }
}
