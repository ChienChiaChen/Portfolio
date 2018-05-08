package com.chiachen.portfolio.network.config;

/**
 * Created by jianjiacheng on 15/03/2018.
 */

public interface ApiEndPoint {
    String GIT_HUB_REPOS = "/users/{user}/repos";

    String LOCAL_SERVER_BASE_URL = "http://172.20.10.3:3000";
    String LOCAL_SERVER_LOGIN = LOCAL_SERVER_BASE_URL + "/members";
    String LOCAL_SERVER_INFO = LOCAL_SERVER_BASE_URL + "/members/{user_id}/info";

    String WEATHER_CITY = "/data/2.5/weather";
}
