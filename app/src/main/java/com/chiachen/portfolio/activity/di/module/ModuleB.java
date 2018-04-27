package com.chiachen.portfolio.activity.di.module;

/**
 * Created by jianjiacheng on 25/04/2018.
 */

public class ModuleB {

    private ModuleC mModuleC;
    private ModuleD mModuleD;

    public ModuleB(ModuleC moduleC, ModuleD moduleD) {
        mModuleC = moduleC;
        mModuleD = moduleD;
    }
}
