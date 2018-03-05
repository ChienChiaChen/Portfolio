package com.chiachen.portfolio.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chiachen.portfolio.presenter.BasePresenter;


public abstract class MvpViewHolder<P extends BasePresenter> extends RecyclerView.ViewHolder {
    protected P presenter;

    public MvpViewHolder(View view) {
        super(view);
    }

    public void bindPresenter(P presenter) {
        this.presenter = presenter;
        presenter.bindView(this);
    }

    public void unbindPresenter() {
        presenter = null;
    }
}
