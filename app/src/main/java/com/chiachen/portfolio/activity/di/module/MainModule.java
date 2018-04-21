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
public class MainModule {
    // @Singleton
    // @Provides
    // public Cloth getCloth() {
    //     Cloth cloth = new Cloth();
    //     cloth.setColor("RED");
    //     return cloth;
    // }
    //
    // @Provides
    // @Named("red")
    // public Cloth getRedCloth() {
    //     Cloth cloth = new Cloth();
    //     cloth.setColor("Red");
    //     return cloth;
    // }
    //
    // @Provides
    // @Named("blue")
    // public Cloth getBlueCloth() {
    //     Cloth cloth = new Cloth();
    //     cloth.setColor("Blue");
    //     return cloth;
    // }
    //
    // @Provides
    // public Clothes getClothes(Cloth cloth){
    //     return new Clothes(cloth);
    // }

    @PerActivity
    @Provides
    public Cloth getRedCloth() {
        Cloth cloth = new Cloth();
        cloth.setColor("红色");
        return cloth;
    }

    @PerActivity
    @Provides
    public ClothHandler getClothHandler(){
        return new ClothHandler();
    }
}
