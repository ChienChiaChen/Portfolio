package com.chiachen.portfolio.network;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

/**
 * Created by jianjiacheng on 17/03/2018.
 */

public class SchedulersTransformer {
    public static <T> ObservableTransformer<T, T> ioToMain() {

        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .subscribeOn(AppSchedulerProvider.io())
                        .unsubscribeOn(AppSchedulerProvider.io())
                        .observeOn(AppSchedulerProvider.ui());
            }
        };
    }
}
