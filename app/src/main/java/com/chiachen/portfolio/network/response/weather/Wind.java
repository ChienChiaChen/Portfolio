package com.chiachen.portfolio.network.response.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jianjiacheng on 07/05/2018.
 */

public class Wind {
    @SerializedName("speed")
    @Expose
    public Float speed;
    @SerializedName("deg")
    @Expose
    public Double deg;

    public Wind withSpeed(Float speed) {
        this.speed = speed;
        return this;
    }

    public Wind withDeg(Double deg) {
        this.deg = deg;
        return this;
    }

}
