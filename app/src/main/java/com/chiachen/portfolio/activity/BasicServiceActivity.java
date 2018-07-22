package com.chiachen.portfolio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.service.BasicService;

public class BasicServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_service);
    }

    public void startService(View view) {
        startService(new Intent(BasicServiceActivity.this, BasicService.class));
    }

    public void stopService(View view) {
        stopService(new Intent(BasicServiceActivity.this, BasicService.class));
    }
}
