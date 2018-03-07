package com.chiachen.portfolio.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chiachen.portfolio.R;

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
}
