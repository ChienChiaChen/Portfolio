package com.chiachen.portfolio.activity.di;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jianjiacheng on 21/04/2018.
 */

@Module
public class MainModule {
    @Provides
    public Cloth getCloth() {
        Cloth cloth = new Cloth();
        cloth.setColor("RED");
        return cloth;
    }
}
