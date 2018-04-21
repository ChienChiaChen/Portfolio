package com.chiachen.portfolio.activity.di;

/**
 * Created by jianjiacheng on 21/04/2018.
 */

public class ClothHandler {
    public Clothes handle(Cloth cloth){
        return new Clothes(cloth);
    }
}
