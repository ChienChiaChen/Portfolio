package com.chiachen.portfolio.network;

import com.chiachen.portfolio.network.config.BaseUrls;
import com.chiachen.portfolio.network.config.HttpConfig;

import java.util.concurrent.TimeUnit;

import io.reactivex.internal.functions.ObjectHelper;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jianjiacheng on 16/03/2018.
 */

public class RetrofitFactory {

    private volatile static RetrofitFactory sRetrofitFactory;
    private Retrofit mRetrofit;

    private RetrofitFactory() {
         mRetrofit = new Retrofit.Builder()
                .baseUrl(BaseUrls.GIT_HUB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getCustomOkHttpClient())
                .build();
    }

    public RetrofitFactory changeBaseUrl(String newApiBaseUrl) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(newApiBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getCustomOkHttpClient())
                .build();

        return sRetrofitFactory;
    }

    public <T> T create(Class<T> clazz) {
        ObjectHelper.requireNonNull(mRetrofit, "Retrofit is null");
        return mRetrofit.create(clazz);
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

    public static OkHttpClient getCustomOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(InterceptorUtil.getLoggingInterceptor())
                .addNetworkInterceptor(InterceptorUtil.getStethoInterceptor())
                .retryOnConnectionFailure(HttpConfig.NEED_TO_RETRY)
                .writeTimeout(HttpConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(HttpConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(HttpConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }
}
