package com.chiachen.portfolio.activity.di.component;

import com.chiachen.portfolio.activity.di.Dagger2ExampleActivity;
import com.chiachen.portfolio.activity.di.annotation.PerActivity;
import com.chiachen.portfolio.activity.di.module.MainModule;

import dagger.Component;

/**
 * Created by jianjiacheng on 21/04/2018.
 */

@PerActivity
@Component(modules = MainModule.class, dependencies = _BaseComponent.class)
public interface MainComponent {
    void inject(Dagger2ExampleActivity dagger2ExampleActivity);
}
