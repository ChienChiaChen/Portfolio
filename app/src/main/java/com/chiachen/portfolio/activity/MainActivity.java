package com.chiachen.portfolio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.activity.rxjava.RxJavaExampleActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startProgressButtonActivity(View view) {
        startActivity(new Intent(MainActivity.this, ProgressButtonActivity.class));
    }

    public void openKeyboardActivity(View view) {
        startActivity(new Intent(MainActivity.this, EditTextActivity.class));
    }

    public void openBottomTabActivity(View view) {
        startActivity(new Intent(MainActivity.this, BottomTabActivity.class));
    }

    public void openMVPActivity(View view) {
        startActivity(new Intent(MainActivity.this, MVPPracticeActivity.class));
    }

    public void openRxJavaRetrofit(View view) {
        startActivity(new Intent(MainActivity.this, RxJavaRetrofitActivity.class));
    }

    public void openRoundImageView(View view) {
        startActivity(new Intent(MainActivity.this, CustomImageActivity.class));
    }

    public void openFlipCardActivity(View view) {
        startActivity(new Intent(MainActivity.this, FlipCardActivity.class));
    }

    public void openTransitionActivity(View view) {
        startActivity(new Intent(MainActivity.this, ActivityTransitionA.class));
    }

    public void openReposListActivity(View view) {
        startActivity(new Intent(MainActivity.this, ReposListActivity.class));
    }

    public void openRxJavaExampleActivity(View view) {
        startActivity(new Intent(MainActivity.this, RxJavaExampleActivity.class));
    }

    public void openTabLayoutPractices(View view) {
        startActivity(new Intent(MainActivity.this, TabLayoutActivity.class));
    }

    public void openBroadcastActivity(View view) {
        startActivity(new Intent(MainActivity.this, BroadcastActivity.class));
    }

    public void openSqliteActivity(View view) {
        startActivity(new Intent(MainActivity.this, SqliteActivity.class));
    }

    public void openServiceActivity(View view) {
        startActivity(new Intent(MainActivity.this, ServiceActivity.class));
    }

    public void openParcelableActivity(View view) {
        startActivity(new Intent(MainActivity.this, ParcelableActivity.class));
    }

    public void openBottomDialogActivity(View view) {
        startActivity(new Intent(MainActivity.this, BottomDialogTestActivity.class));
    }
}
