package com.chiachen.portfolio.activity.di.module;

import com.chiachen.portfolio.activity.di.Cloth;
import com.chiachen.portfolio.activity.di.ClothHandler;
import com.chiachen.portfolio.activity.di.annotation.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jianjiacheng on 21/04/2018.
 */

@Module
public class SecondModule {
    @PerActivity
    @Provides
    public Cloth getBlueCloth(){
        Cloth cloth = new Cloth();
        cloth.setColor("蓝色");
        return cloth;
    }

    @PerActivity
    @Provides
    public ClothHandler getClothHandler(){
        return new ClothHandler();
    }
}
