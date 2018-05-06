package com.chiachen.portfolio.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.base._MVPActivity;
import com.chiachen.portfolio.presenter.CountryWallPresenter;
import com.chiachen.portfolio.view.CountryWallView;

public class CountryWallActivity extends _MVPActivity<CountryWallPresenter> implements CountryWallView {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    @Override
    protected CountryWallPresenter createPresenter() {
        return new CountryWallPresenter(this);
    }

    @Override
    protected void initUI() {
        setContentView(R.layout.activity_country_wall);
        mRecyclerView = findViewById(R.id.recycler_view);
        mProgressBar = findViewById(R.id.progress);
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
