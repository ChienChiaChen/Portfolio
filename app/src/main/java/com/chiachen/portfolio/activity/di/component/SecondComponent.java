package com.chiachen.portfolio.activity.di.component;

import com.chiachen.portfolio.activity.di.SecondActivity;
import com.chiachen.portfolio.activity.di.annotation.PerActivity;
import com.chiachen.portfolio.activity.di.module.SecondModule;

import dagger.Component;

/**
 * Created by jianjiacheng on 21/04/2018.
 */

@PerActivity
@Component(modules = SecondModule.class)
public interface SecondComponent {
    void inject(SecondActivity secondActivity);
}
