package com.chiachen.portfolio.network.Service;

import com.chiachen.portfolio.network.Service.entity.HttpResult;
import com.chiachen.portfolio.network.Service.entity.Subject;

import java.util.List;
import rx.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jianjiacheng on 07/03/2018.
 */

public interface MovieService {

    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);
}
