package com.chiachen.portfolio.activity;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.DecelerateInterpolator;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.utils.Constant;


public class ActivityTransitionB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_b);

        ViewCompat.animate(findViewById(R.id.text_b))
                .scaleY(1)
                .scaleX(1)
                .setStartDelay(Constant.ITEM_DELAY)
                .setDuration(Constant.ANIM_ITEM_DURATION / 2)
                .setInterpolator(new DecelerateInterpolator())
                .start();
    }
}
