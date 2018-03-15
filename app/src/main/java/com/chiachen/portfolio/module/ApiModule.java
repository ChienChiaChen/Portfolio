package com.chiachen.portfolio.module;

import com.chiachen.portfolio.network.BaseUrls;
import com.chiachen.portfolio.network.Service.GitHubService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jianjiacheng on 15/03/2018.
 */

@Module
public class ApiModule {

    @Provides
    @Singleton
    protected GitHubService provideGitHubService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrls.GITHUB_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(GitHubService.class);
    }
}
