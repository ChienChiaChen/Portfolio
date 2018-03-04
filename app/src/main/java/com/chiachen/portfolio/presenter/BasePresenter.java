package com.chiachen.portfolio.presenter;

import com.chiachen.portfolio.view.IBaseView;

import java.lang.ref.WeakReference;

/**
 * Created by jianjiacheng on 04/03/2018.
 */

public abstract class BasePresenter<M, V extends IBaseView> {
    protected M model;
    private WeakReference<V> view;

    protected abstract void updateView();

    public BasePresenter(V view) {
        this.view = new WeakReference<>(view);
        getView().init();
    }

    public void syncView() {
        if (setupDone()) {
            updateView();
        }
    }

    public void unbindView() {
        this.view = null;
    }

    public void setModel(M model) {
        this.model = model;
        if (setupDone()) {
            updateView();
        }
    }

    private boolean setupDone() {
        return null != getView() && null != model;
    }

    public V getView() {
        return (null == view) ? null : view.get();
    }
}
