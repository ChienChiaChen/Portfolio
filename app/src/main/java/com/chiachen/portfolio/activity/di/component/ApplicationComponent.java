package com.chiachen.portfolio.activity.di.component;

import com.chiachen.portfolio.activity.di.module.ApplicationModule;
import com.chiachen.portfolio.activity.di.module.ModuleA;

import dagger.Component;

/**
 * Created by jianjiacheng on 25/04/2018.
 */

@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    ModuleA moduleA();
}
