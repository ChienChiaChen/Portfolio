package com.chiachen.portfolio.network.service;

import com.chiachen.portfolio.network.config.ApiEndPoint;
import com.chiachen.portfolio.network.response.weather.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jianjiacheng on 07/05/2018.
 */

public interface WeatherService {
    String APP_KEY = "5ed10b1bc6b40772f306270894560b36";

    @GET(ApiEndPoint.WEATHER_CITY)
    Observable<WeatherResponse> requestWeatherFromCity(@Query("q") String q, @Query("APPID")String appid);

}
