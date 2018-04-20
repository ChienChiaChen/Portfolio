package com.chiachen.portfolio.activity.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jianjiacheng on 21/04/2018.
 */

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(Dagger2ExampleActivity dagger2ExampleActivity);
}
