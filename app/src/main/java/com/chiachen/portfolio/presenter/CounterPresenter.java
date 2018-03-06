package com.chiachen.portfolio.presenter;

import com.chiachen.portfolio.models.Counter;
import com.chiachen.portfolio.view.CounterView;

public class CounterPresenter extends BasePresenter<Counter, CounterView> {
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 99;

    @Override
    protected void updateView() {
        getView().setCounterName(model.getName());
    }

    public void onCounterClicked() {
        if (setupDone()) {
            getView().goToDetailView(model);//
        }
    }
}
