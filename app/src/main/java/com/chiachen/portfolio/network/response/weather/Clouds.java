package com.chiachen.portfolio.network.response.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jianjiacheng on 07/05/2018.
 */

public class Clouds {
    @SerializedName("all")
    @Expose
    public Integer all;

    public Clouds withAll(Integer all) {
        this.all = all;
        return this;
    }

}
