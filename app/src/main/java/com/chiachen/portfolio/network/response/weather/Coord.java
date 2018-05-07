package com.chiachen.portfolio.network.response.weather;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jianjiacheng on 07/05/2018.
 */

public class Coord {
    @SerializedName("lon")
    @Expose
    public Float lon;
    @SerializedName("lat")
    @Expose
    public Float lat;

    public Coord withLon(Float lon) {
        this.lon = lon;
        return this;
    }

    public Coord withLat(Float lat) {
        this.lat = lat;
        return this;
    }
}
