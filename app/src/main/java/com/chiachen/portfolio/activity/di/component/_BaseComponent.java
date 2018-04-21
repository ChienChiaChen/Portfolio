package com.chiachen.portfolio.activity.di.component;

import com.chiachen.portfolio.activity.di.ClothHandler;
import com.chiachen.portfolio.activity.di.module.BaseModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jianjiacheng on 21/04/2018.
 */

// BaseComponent 是給其他 Component 依賴的
// 所以可以不用Inject

@Singleton
@Component(modules = BaseModule.class)
public interface _BaseComponent {
    ClothHandler getClothHandler();
}
