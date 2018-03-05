package com.chiachen.portfolio.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.models.Counter;
import com.chiachen.portfolio.presenter.CounterPresenter;


public class CounterAdapter extends MvpRecyclerListAdapter<Counter, CounterPresenter, CounterViewHolder> {
    @Override
    public CounterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_counter_row, parent, false);
        return new CounterViewHolder(view);
    }

    @NonNull
    @Override
    protected CounterPresenter createPresenter(@NonNull Counter counter) {
        CounterPresenter presenter = new CounterPresenter();
        presenter.setModel(counter);
        return presenter;
    }

    @NonNull
    @Override
    protected Object getModelId(@NonNull Counter model) {
        return model.getId();
    }
}
