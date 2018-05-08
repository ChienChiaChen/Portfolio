package com.chiachen.portfolio.presenter;

import com.chiachen.portfolio.base._BasePresenter;
import com.chiachen.portfolio.network.RetrofitFactory;
import com.chiachen.portfolio.network.config.BaseUrls;
import com.chiachen.portfolio.network.response.weather.WeatherResponse;
import com.chiachen.portfolio.network.service.WeatherService;
import com.chiachen.portfolio.network.subscribers._ApiCallback;
import com.chiachen.portfolio.view.WeatherView;

/**
 * Created by jianjiacheng on 06/05/2018.
 */

public class WeatherPresenter extends _BasePresenter<WeatherView> {

    public WeatherPresenter(WeatherView weatherView) {
        attachView(weatherView);
    }

    public void loadData(String cityName) {
        getView().showLoading();
        addSubscription(RetrofitFactory.getInstance()
                        .changeBaseUrl(BaseUrls.OPEN_WEATHER_BASE_URL)
                        .create(WeatherService.class)
                        .requestWeatherFromCity(cityName, WeatherService.APP_KEY),
                            new _ApiCallback<WeatherResponse>(){
                                @Override
                                public void onSuccess(WeatherResponse model) {
                                    if (isViewAttached()) {
                                        getView().getDataSuccess(model);
                                    }
                                }

                                @Override
                                public void onFailure(String msg) {
                                    if (isViewAttached()) {
                                        getView().getDataFail(msg);
                                    }
                                }

                                @Override
                                public void onFinish() {
                                    if (isViewAttached()) {
                                        getView().hideLoading();
                                    }
                                }
                            });
    }
}
