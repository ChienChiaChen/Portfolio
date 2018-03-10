package com.chiachen.portfolio.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.utils.Constant;
import com.chiachen.portfolio.utils.tool.DensityUtil;

public class ActivityTransitionA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_a);

        ViewCompat.animate(findViewById(R.id.text_a))
                .translationY(-(DensityUtil.dp2px(ActivityTransitionA.this,40)))// 80dp
                .setStartDelay(Constant.STARTUP_DELAY)
                .setDuration(Constant.ANIM_ITEM_DURATION)
                .setInterpolator(new DecelerateInterpolator(1.2f))
                .start();
    }

    public void openTransitionB(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                    this,
                    findViewById(R.id.fab_a),
                    findViewById(R.id.fab_a).getTransitionName());

            startActivity(new Intent(this, ActivityTransitionB.class), options.toBundle());
        } else {
            startActivity(new Intent(this, ActivityTransitionB.class));
        }
    }
}
