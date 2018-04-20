package com.chiachen.portfolio.activity.di;

/**
 * Created by jianjiacheng on 21/04/2018.
 */

public class Cloth {
    private String color;
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    @Override
    public String toString() {
        return color + " material";
    }
}
