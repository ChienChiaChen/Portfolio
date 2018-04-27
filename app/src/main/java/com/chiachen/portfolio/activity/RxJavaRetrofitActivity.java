package com.chiachen.portfolio.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.base._MVPActivity;
import com.chiachen.portfolio.base._MainPresenter;
import com.chiachen.portfolio.base._MainView;
import com.chiachen.portfolio.network.response.Repo;

import java.util.ArrayList;


// Refer to: https://gank.io/post/56e80c2c677659311bed9841
// Refer to:
public class RxJavaRetrofitActivity extends _MVPActivity<_MainPresenter> implements _MainView {

    @Override
    protected _MainPresenter createPresenter() {
        return new _MainPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_retrofit);

        findViewById(R.id.click_me).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.loadData("JasonChienPrenetics");
            }
        });
    }

    @Override
    public void getDataSuccess(ArrayList<Repo> model) {
        Toast.makeText(RxJavaRetrofitActivity.this, "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getDataFail(String msg) {
        Toast.makeText(RxJavaRetrofitActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
