package com.chiachen.portfolio.network.Service;

import com.chiachen.portfolio.network.ApiEndPoint;
import com.chiachen.portfolio.network.response.Repo;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jianjiacheng on 15/03/2018.
 */

public interface GitHubService {

    @GET(ApiEndPoint.GITHUB_REPOS)
    Observable<ArrayList<Repo>> getRepoData(@Path("user") String user);
}
