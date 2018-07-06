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

    public void openMergeActivity(View view) {
        startActivity(new Intent(RxJavaExampleActivity.this, MergeExampleActivity.class));
    }

    public void openErrorHandle(View view) {
        startActivity(new Intent(RxJavaExampleActivity.this, ErrorHandleActivity.class));
    }

    public void openZipActivity(View view) {
        startActivity(new Intent(RxJavaExampleActivity.this, RxJavaZipActivity.class));
    }

    public void openConcatActivity(View view) {
        startActivity(new Intent(RxJavaExampleActivity.this, RxJavaConcatActivity.class));
    }

    public void openDistinctActivity(View view) {
        startActivity(new Intent(RxJavaExampleActivity.this, RxJavaDistinctActivity.class));
    }

    public void openFilterActivity(View view) {
        startActivity(new Intent(RxJavaExampleActivity.this, RxJavaFilterActivity.class));
    }

    public void openBufferActivity(View view) {
        startActivity(new Intent(RxJavaExampleActivity.this, RxJavaBufferActivity.class));
    }

    public void openTimerActivity(View view) {
        startActivity(new Intent(RxJavaExampleActivity.this, RxJavaTimerActivity.class));
    }

    public void openIntervalActivity(View view) {
        startActivity(new Intent(RxJavaExampleActivity.this, RxJavaIntervalActivity.class));
    }

    public void openDoOnNextActivity(View view) {
        startActivity(new Intent(RxJavaExampleActivity.this, RxJavaDoOnNextActivity.class));
    }

    public void openSkipActivity(View view) {
        startActivity(new Intent(RxJavaExampleActivity.this, RxJavaSkipActivity.class));
    }

    public void openTakeActivity(View view) {
        startActivity(new Intent(RxJavaExampleActivity.this, RxJavaTakeActivity.class));
    }

    public void openSingleActivity(View view) {
        startActivity(new Intent(RxJavaExampleActivity.this, RxJavaSingleActivity.class));
    }

    public void openDebounceActivity(View view) {
        startActivity(new Intent(RxJavaExampleActivity.this, RxJavaDebounceActivity.class));
    }

}
