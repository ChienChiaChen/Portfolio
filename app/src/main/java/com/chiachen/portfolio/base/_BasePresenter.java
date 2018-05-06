package com.chiachen.portfolio.base;

import android.support.annotation.NonNull;

import com.chiachen.portfolio.network.AppSchedulerProvider;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by jianjiacheng on 27/04/2018.
 */

public class _BasePresenter<V> {
    private WeakReference<V> mView;
    private CompositeDisposable mCompositeDisposable;

    public void attachView(@NonNull V view) {
        mView = new WeakReference<>(view);
    }

    public boolean isViewAttached() {
        return null != mView && null != mView.get();
    }

    public V getView() {
        return this.isViewAttached() ? null : mView.get();
    }

    public void detachView() {
        this.mView = null;
        onUnSubscribe();
    }

    private void onUnSubscribe() {
        if (null == mCompositeDisposable) return;
        mCompositeDisposable.dispose();
    }

    public void addSubscription(Observable observable, DisposableObserver observer) {
        if (null == mCompositeDisposable) {
            mCompositeDisposable = new CompositeDisposable();
        }

        mCompositeDisposable.add(observer);
        observable.subscribeOn(AppSchedulerProvider.io())
                .observeOn(AppSchedulerProvider.ui())
                .subscribeWith(observer);
    }
}
