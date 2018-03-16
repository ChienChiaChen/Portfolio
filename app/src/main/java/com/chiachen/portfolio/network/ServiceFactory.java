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
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    private static LoggingInterceptor getLoggingInterceptor() {
        return (new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request("Request")
                .response("Response")
                .addHeader("version", BuildConfig.VERSION_NAME)
                .build());
    }
}
