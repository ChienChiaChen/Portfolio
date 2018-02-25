package com.chiachen.portfolio;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import presenter.IProgressButtonPresenter;
import presenter.ProgressPresenter;
import view.IProgressButtonView;

public class ProgressButtonActivity extends AppCompatActivity implements IProgressButtonView {

    public final String TAG = this.getClass().getSimpleName();
    private static final int ANIMATION_TIME = 2500;
    private IProgressButtonPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_button);
        mPresenter = new ProgressPresenter(this);
        mPresenter.init();
    }

    @Override
    public void init() {
        getTextView().setText(R.string.progress_button_download);
        getButton().setSelected(false);
        getButton().setEnabled(true);
        setButtonClickListener(onClickListener, getButton());
    }

    private View getButton() {
        return findViewById(R.id.action_button);
    }

    private TextView getTextView() {
        return (TextView) findViewById(R.id.action_button_text);
    }

    @Override
    public void handleDownloading() {
        getProgressBar().setProgress(0);
        getProgressBar().setVisibility(View.VISIBLE);
        getTextView().setText(R.string.progress_button_downloading);
        updateButtonProgress(100);
    }

    private ProgressBar getProgressBar() {
        return (ProgressBar) findViewById(R.id.action_button_progress);
    }

    @Override
    public void handleDownloadEnd() {
        getTextView().setText(R.string.progress_button_downloaded);
        getButton().setSelected(true);
        getButton().setEnabled(false);
        getProgressBar().setVisibility(View.GONE);
    }

    private void updateButtonProgress(int progressTo){
        ObjectAnimator animation = ObjectAnimator.ofInt(
                getProgressBar(),
                "progress",
                getProgressBar().getProgress(),
                progressTo);

        animation.setDuration(ANIMATION_TIME);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (100 == getProgressBar().getProgress()) {
                    mPresenter.handleDownloadEnd();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animation.start();
    }

    private void setButtonClickListener(View.OnClickListener listener, View... buttons) {
        for (View button : buttons) {
            if (button != null)
                button.setOnClickListener(listener);
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.action_button: {
                    mPresenter.handleDownloading();
                    break;
                }
            }
        }
    };
}
