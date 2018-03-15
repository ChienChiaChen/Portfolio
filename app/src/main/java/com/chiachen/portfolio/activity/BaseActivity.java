package com.chiachen.portfolio.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (null != getSupportActionBar()) {
            getSupportActionBar().setTitle(this.getClass().getSimpleName());
        }
    }

    public void showLoading() {

    }

    public void hideLoading() {

    }
}
