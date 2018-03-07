package com.chiachen.portfolio.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.chiachen.portfolio.R;

public class FlipCardActivity extends AppCompatActivity {

    private FrameLayout mFrameLayoutContainer;
    private FrameLayout mFrameLayoutBack;
    private FrameLayout mFrameLayoutFront;

    private AnimatorSet mRightOut;
    private AnimatorSet mLeftIn;

    private boolean mIsShowBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_card);
        mFrameLayoutBack = findViewById(R.id.main_fl_card_back);
        mFrameLayoutFront = findViewById(R.id.main_fl_card_front);
        mFrameLayoutContainer = findViewById(R.id.cell_card_container);

        setAnimators();
        setCameraDistance();
    }

    private void setAnimators() {
        mRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.anim_out);
        mLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.anim_in);

        mRightOut.addListener(new AnimatorListenerAdapter() {
            @Override public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mFrameLayoutContainer.setClickable(false);
            }
        });
        mLeftIn.addListener(new AnimatorListenerAdapter() {
            @Override public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mFrameLayoutContainer.setClickable(true);
            }
        });
    }

    private void setCameraDistance() {
        int distance = 16000;
        float scale = getResources().getDisplayMetrics().density * distance;
        mFrameLayoutFront.setCameraDistance(scale);
        mFrameLayoutBack.setCameraDistance(scale);
    }


    public void flipCard(View view) {
        // 正面朝上
        if (!mIsShowBack) {
            mRightOut.setTarget(mFrameLayoutFront);
            mLeftIn.setTarget(mFrameLayoutBack);
            mRightOut.start();
            mLeftIn.start();
            mIsShowBack = true;
        } else { // 背面朝上
            mRightOut.setTarget(mFrameLayoutBack);
            mLeftIn.setTarget(mFrameLayoutFront);
            mRightOut.start();
            mLeftIn.start();
            mIsShowBack = false;
        }
    }
}
