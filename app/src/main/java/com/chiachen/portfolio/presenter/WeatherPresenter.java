package com.chiachen.portfolio.presenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.SearchView;

import com.chiachen.portfolio.base._BasePresenter;
import com.chiachen.portfolio.network.RetrofitFactory;
import com.chiachen.portfolio.network.config.BaseUrls;
import com.chiachen.portfolio.network.response.weather.WeatherResponse;
import com.chiachen.portfolio.network.service.WeatherService;
import com.chiachen.portfolio.network.subscribers._ApiCallback;
import com.chiachen.portfolio.view.WeatherView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by jianjiacheng on 06/05/2018.
 */

public class WeatherPresenter extends _BasePresenter<WeatherView> {
    public static final String TAG = "WeatherPresenter";
    public static final int TIME_DEBOUNCE = 2;

    public WeatherPresenter(WeatherView weatherView) {
        attachView(weatherView);
    }

    public void loadData(String cityName) {
        getView().showLoading();
        addSubscription(getObservable(cityName), getObserver());
    }

    private Observable<WeatherResponse> getObservable(String cityName) {
        return RetrofitFactory.getInstance()
                        .changeBaseUrl(BaseUrls.OPEN_WEATHER_BASE_URL)
                        .create(WeatherService.class)
                        .requestWeatherFromCity(cityName, WeatherService.APP_KEY);
    }

    public void getResultsBasedOnQuery(SearchView searchView) {
        addSubscription(getSearchObserver(searchView), getObserver());
    }

    private Observable<WeatherResponse> getSearchObserver(SearchView searchView) {
        return getObservableQuery(searchView)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(@NonNull String s) throws Exception {
                        return !s.equals("");
                    }
                })
                .debounce(TIME_DEBOUNCE, TimeUnit.SECONDS)
                .distinctUntilChanged()
                .switchMap(new Function<String, ObservableSource<WeatherResponse>>() {
                    @Override
                    public Observable<WeatherResponse> apply(@NonNull String s) throws Exception {
                        return getObservable(s);
                    }
                });
    }

    private Observable<String> getObservableQuery(SearchView searchView) {
        final PublishSubject<String> publishSubject = PublishSubject.create();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                publishSubject.onNext(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                publishSubject.onNext(newText);
                return true;
            }
        });
        return publishSubject;
    }

    public DisposableObserver<WeatherResponse> getObserver() {
        return new _ApiCallback<WeatherResponse>(){
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
            }};
    }
}
