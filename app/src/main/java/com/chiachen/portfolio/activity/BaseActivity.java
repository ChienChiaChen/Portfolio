package com.chiachen.portfolio.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

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

    protected void logJJ(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }

        Log.e("JASON_CHIEN", "\n"+msg);
    }
}
