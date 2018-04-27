package com.chiachen.portfolio.base;


import com.chiachen.portfolio.network.response.Repo;

import java.util.ArrayList;

/**
 * Created by jianjiacheng on 25/04/2018.
 */

public interface _MainView extends _BaseView {
    void getDataSuccess(ArrayList<Repo> model);
    void getDataFail(String msg);
}
