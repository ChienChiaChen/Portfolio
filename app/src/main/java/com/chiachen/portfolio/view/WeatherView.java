package com.chiachen.portfolio.view;

import com.chiachen.portfolio.base._BaseView;
import com.chiachen.portfolio.network.response.weather.WeatherResponse;

/**
 * Created by jianjiacheng on 06/05/2018.
 */

public interface WeatherView extends _BaseView {
    void getDataSuccess(WeatherResponse model);

    void getDataFail(String msg);
}
