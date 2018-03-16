package com.chiachen.portfolio.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jianjiacheng on 16/03/2018.
 */

public class RetrofitFactory {

    private static RetrofitFactory sRetrofitFactory;
    private static APIFunction sAPIFunction;

    private RetrofitFactory() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrls.GIT_HUB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getCustomOkHttpClient())
                .build();

        sAPIFunction = retrofit.create(APIFunction.class);
    }

    public static RetrofitFactory getInstance() {
        if (null == sRetrofitFactory) {
            synchronized (RetrofitFactory.class) {
                if (null == sRetrofitFactory) {
                    sRetrofitFactory = new RetrofitFactory();
                }
            }
        }
        return sRetrofitFactory;
    }

    public APIFunction API() {
        return sAPIFunction;
    }

    public static OkHttpClient getCustomOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(InterceptorUtil.getLoggingInterceptor())
                .addNetworkInterceptor(InterceptorUtil.getStethoInterceptor())
                .retryOnConnectionFailure(HttpConfig.needToRetry)
                .writeTimeout(HttpConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(HttpConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(HttpConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }
}
