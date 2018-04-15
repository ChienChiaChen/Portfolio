package com.chiachen.portfolio.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jianjiacheng on 15/04/2018.
 */

public class InfoRepository {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("height")
    @Expose
    public String height;
    @SerializedName("weight")
    @Expose
    public String weight;

    public InfoRepository withId(String id) {
        this.id = id;
        return this;
    }

    public InfoRepository withHeight(String height) {
        this.height = height;
        return this;
    }

    public InfoRepository withWeight(String weight) {
        this.weight = weight;
        return this;
    }

}
