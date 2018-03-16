package com.chiachen.portfolio.network;

import com.chiachen.portfolio.network.response.Repo;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jianjiacheng on 16/03/2018.
 */

public interface APIFunction {

    @GET(ApiEndPoint.GIT_HUB_REPOS)
    Observable<ArrayList<Repo>> getRepoData(@Path("user") String user);
}
