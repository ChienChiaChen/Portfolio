package com.chiachen.portfolio.activity.rxjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.activity.BaseActivity;

public class RxJavaExampleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_example);
    }

    public void openLessonCreate(View view) {
        startActivity(new Intent(RxJavaExampleActivity.this, RxJavaCreateActivity.class));
    }

    public void openLessonMap(View view) {
        startActivity(new Intent(RxJavaExampleActivity.this, RxJavaMapActivity.class));
    }

    public void openLessonFlatMap(View view) {
        startActivity(new Intent(RxJavaExampleActivity.this, RxJavaFlatMapActivity.class));
    }

    public void openLessonConcatMap(View view) {
        startActivity(new Intent(RxJavaExampleActivity.this, RxJavaConcatMapActivity.class));
    }

    public void openThrottle(View view) {
        startActivity(new Intent(RxJavaExampleActivity.this, ThrottleActivity.class));

    }
}
