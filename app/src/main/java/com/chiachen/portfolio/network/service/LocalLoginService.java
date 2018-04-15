package com.chiachen.portfolio.network.service;

import com.chiachen.portfolio.data.InfoRepository;
import com.chiachen.portfolio.data.UserRepository;
import com.chiachen.portfolio.network.config.ApiEndPoint;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jianjiacheng on 15/04/2018.
 */


public interface LocalLoginService {

    @GET(ApiEndPoint.LOCAL_SERVER_LOGIN)
    Observable<List<UserRepository>> requestLogin(@Query("name") String name, @Query("pwd")String pwd);

    @GET(ApiEndPoint.LOCAL_SERVER_INFO)
    Observable<List<InfoRepository>> getInfo(@Path("user_id") String userId);
}
