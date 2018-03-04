package com.chiachen.portfolio.activity;

import android.os.Bundle;
import android.widget.ViewAnimator;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.presenter.MVPPracticePresenter;
import com.chiachen.portfolio.presenter.PresenterManager;
import com.chiachen.portfolio.view.IMVPPracticeView;

public class MVPPracticeActivity extends BaseActivity implements IMVPPracticeView {
    private static final int POSITION_LOADING = 1;
    private static final int POSITION_EMPTY = 2;

    private MVPPracticePresenter mPresenter;
    private ViewAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (null == savedInstanceState) {
            mPresenter = new MVPPracticePresenter(this);
        } else {
            mPresenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }
    }

    @Override
    public void init() {
        setContentView(R.layout.activity_mvp_practice);
        animator = findViewById(R.id.animator);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.syncView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unbindView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        PresenterManager.getInstance().setPresenter(mPresenter, outState);
    }

    @Override
    public void showLoading() {
        animator.setDisplayedChild(POSITION_LOADING);
    }

    @Override
    public void showEmpty() {
        animator.setDisplayedChild(POSITION_EMPTY);
    }
}
