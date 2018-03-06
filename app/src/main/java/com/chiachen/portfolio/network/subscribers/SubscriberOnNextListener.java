package com.chiachen.portfolio.network.subscribers;

/**
 * Created by jianjiacheng on 07/03/2018.
 */

public interface SubscriberOnNextListener<T> {
    void onNext(T t);
}
