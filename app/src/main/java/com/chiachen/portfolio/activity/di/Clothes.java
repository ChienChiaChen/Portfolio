package com.chiachen.portfolio.activity.di;

/**
 * Created by jianjiacheng on 21/04/2018.
 */

public class Clothes {
    private Cloth cloth;
    public Clothes(Cloth cloth) {
        this.cloth = cloth;
    }
    public Cloth getCloth() {
        return cloth;
    }
    @Override
    public String toString() {
        return cloth.getColor() + "衣服";
    }
}
