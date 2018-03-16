package com.chiachen.portfolio.network;

import com.chiachen.portfolio.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jianjiacheng on 16/03/2018.
 */

public class ServiceFactory {
    private static final int DEFAULT_TIMEOUT = 15;
    public static final String LOG_REQUEST = "LOG_REQUEST";
    public static final String LOG_RESPONSE = "LOG_RESPONSE";
    public static final String LOG_HEADER = "LOG_HEADER";

    public static <T> T createServiceFrom(final Class<T> serviceClass, String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getCustomOkHttpClient())
                .build();

        return retrofit.create(serviceClass);
    }

    public static OkHttpClient getCustomOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(getLoggingInterceptor())
                .addNetworkInterceptor(new StethoInterceptor())
                .retryOnConnectionFailure(true)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    private static LoggingInterceptor getLoggingInterceptor() {
        return (new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request(LOG_REQUEST)
                .response(LOG_RESPONSE)
                .addHeader(LOG_HEADER, BuildConfig.VERSION_NAME)
                .build());
    }
}
