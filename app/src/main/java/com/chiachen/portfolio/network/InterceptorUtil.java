package com.chiachen.portfolio.network;

import com.chiachen.portfolio.BuildConfig;
import com.chiachen.portfolio.network.config.HttpConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;

/**
 * Created by jianjiacheng on 16/03/2018.
 */

public class InterceptorUtil {

    public static LoggingInterceptor getLoggingInterceptor() {
        return (new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request(HttpConfig.TAG_LOG_REQUEST)
                .response(HttpConfig.TAG_LOG_RESPONSE)
                .addHeader(HttpConfig.TAG_LOG_HEADER, BuildConfig.VERSION_NAME)
                .build());
    }

    public static StethoInterceptor getStethoInterceptor() {
        return new StethoInterceptor();
    }

    public static Interceptor HeaderInterceptor() {
        return  new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request mRequest = chain.request(); // For token
                    return chain.proceed(mRequest);
                }
            };
    }

}
