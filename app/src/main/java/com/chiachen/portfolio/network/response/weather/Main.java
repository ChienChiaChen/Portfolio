package com.chiachen.portfolio.network.response.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jianjiacheng on 07/05/2018.
 */

public class Main {
    @SerializedName("temp")
    @Expose
    public Float temp;
    @SerializedName("pressure")
    @Expose
    public Double pressure;
    @SerializedName("humidity")
    @Expose
    public Integer humidity;
    @SerializedName("temp_min")
    @Expose
    public Float tempMin;
    @SerializedName("temp_max")
    @Expose
    public Float tempMax;

    public Main withTemp(Float temp) {
        this.temp = temp;
        return this;
    }

    public Main withPressure(Double pressure) {
        this.pressure = pressure;
        return this;
    }

    public Main withHumidity(Integer humidity) {
        this.humidity = humidity;
        return this;
    }

    public Main withTempMin(Float tempMin) {
        this.tempMin = tempMin;
        return this;
    }

    public Main withTempMax(Float tempMax) {
        this.tempMax = tempMax;
        return this;
    }

}
