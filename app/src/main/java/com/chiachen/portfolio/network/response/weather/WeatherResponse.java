package com.chiachen.portfolio.network.response.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jianjiacheng on 07/05/2018.
 */

public class WeatherResponse {
    @SerializedName("coord")
    @Expose
    public Coord coord;
    @SerializedName("weather")
    @Expose
    public List<Weather> weather = null;
    @SerializedName("base")
    @Expose
    public String base;
    @SerializedName("main")
    @Expose
    public Main main;
    @SerializedName("visibility")
    @Expose
    public Integer visibility;
    @SerializedName("wind")
    @Expose
    public Wind wind;
    @SerializedName("clouds")
    @Expose
    public Clouds clouds;
    @SerializedName("dt")
    @Expose
    public Integer dt;
    @SerializedName("sys")
    @Expose
    public Sys sys;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("cod")
    @Expose
    public Integer cod;

    public WeatherResponse withCoord(Coord coord) {
        this.coord = coord;
        return this;
    }

    public WeatherResponse withWeather(List<Weather> weather) {
        this.weather = weather;
        return this;
    }

    public WeatherResponse withBase(String base) {
        this.base = base;
        return this;
    }

    public WeatherResponse withMain(Main main) {
        this.main = main;
        return this;
    }

    public WeatherResponse withVisibility(Integer visibility) {
        this.visibility = visibility;
        return this;
    }

    public WeatherResponse withWind(Wind wind) {
        this.wind = wind;
        return this;
    }

    public WeatherResponse withClouds(Clouds clouds) {
        this.clouds = clouds;
        return this;
    }

    public WeatherResponse withDt(Integer dt) {
        this.dt = dt;
        return this;
    }

    public WeatherResponse withSys(Sys sys) {
        this.sys = sys;
        return this;
    }

    public WeatherResponse withId(Integer id) {
        this.id = id;
        return this;
    }

    public WeatherResponse withName(String name) {
        this.name = name;
        return this;
    }

    public WeatherResponse withCod(Integer cod) {
        this.cod = cod;
        return this;
    }
}
