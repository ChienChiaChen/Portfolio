package com.chiachen.portfolio.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.models.Counter;
import com.chiachen.portfolio.presenter.CounterPresenter;
import com.chiachen.portfolio.view.CounterView;


public class CounterViewHolder extends MvpViewHolder<CounterPresenter> implements CounterView {
    private final TextView counterName;

    @Nullable
    private OnCounterClickListener listener;

    public CounterViewHolder(View itemView) {
        super(itemView);
        counterName = (TextView) itemView.findViewById(R.id.counter_name);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onCounterClicked();
            }
        });
    }

    public void setListener(@Nullable OnCounterClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void setCounterName(String name) {
        counterName.setText(name);
    }

    @Override
    public void goToDetailView(Counter counter) {
        if (listener != null) {
            listener.onCounterClick(counter);
        }
    }

    public interface OnCounterClickListener {
        void onCounterClick(Counter counter);
    }
}
