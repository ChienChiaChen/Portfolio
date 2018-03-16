package com.chiachen.portfolio.network;

/**
 * Created by jianjiacheng on 16/03/2018.
 */

public interface HttpConfig {
    int DEFAULT_TIMEOUT = 15;
    String LOG_REQUEST = "LOG_REQUEST";
    String LOG_RESPONSE = "LOG_RESPONSE";
    String LOG_HEADER = "LOG_HEADER";
    boolean needToRetry = true;
}
