package com.chiachen.portfolio.activity.di;

import dagger.Component;

/**
 * Created by jianjiacheng on 21/04/2018.
 */

@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(Dagger2ExampleActivity dagger2ExampleActivity);
}
