package com.chiachen.portfolio.view;


import com.chiachen.portfolio.models.Counter;

public interface CounterView {
    void setCounterName(String name);
    void goToDetailView(Counter counter);
}
