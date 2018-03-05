package com.chiachen.portfolio.presenter;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

/**
 * Created by jianjiacheng on 04/03/2018.
 */

public abstract class BasePresenter<M, V> {
    protected M model;
    private WeakReference<V> view;

    protected abstract void updateView();

    public void bindView(@NonNull V view) {
        this.view = new WeakReference<>(view);

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

    protected boolean setupDone() {
        return null != getView() && null != model;
    }

    public V getView() {
        return (null == view) ? null : view.get();
    }
}
