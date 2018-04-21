package com.chiachen.portfolio.activity.di.module;

import com.chiachen.portfolio.activity.di.ClothHandler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jianjiacheng on 21/04/2018.
 */

@Module
public class BaseModule {
    @Singleton
    @Provides
    public ClothHandler getClothHandler(){
        return new ClothHandler();
    }
}
