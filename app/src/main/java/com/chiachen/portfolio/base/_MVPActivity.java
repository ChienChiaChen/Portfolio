package com.chiachen.portfolio.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by jianjiacheng on 27/04/2018.
 */

public abstract class _MVPActivity<P extends _BasePresenter> extends _BaseActivity {
    protected P mPresenter;
    protected abstract P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        initUI();
    }

    protected abstract void initUI();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }

    public void showLoading() {
        showProgressDialog();
    }

    public void hideLoading() {
        dismissProgressDialog();
    }
}
