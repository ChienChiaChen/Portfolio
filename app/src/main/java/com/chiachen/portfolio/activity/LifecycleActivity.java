package com.chiachen.portfolio.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.chiachen.portfolio.R;

public class LifecycleActivity extends AppCompatActivity {

    private TextView mTxtLifecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        mTxtLifecycle = findViewById(R.id.txt_lifecycle);
        mTxtLifecycle.setText(new StringBuffer().append(mTxtLifecycle.getText()).append("\n").append("OnCreate"));
        Log.d("JASON_CHIEN", "\nonCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        mTxtLifecycle.setText(new StringBuffer().append(mTxtLifecycle.getText()).append("\n").append("onStart"));
        Log.d("JASON_CHIEN", "\nonStart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mTxtLifecycle.setText(new StringBuffer().append(mTxtLifecycle.getText()).append("\n").append("onRestoreInstanceState"));
        Log.d("JASON_CHIEN", "\nonRestoreInstanceState");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTxtLifecycle.setText(new StringBuffer().append(mTxtLifecycle.getText()).append("\n").append("onResume"));
        Log.d("JASON_CHIEN", "\nonResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mTxtLifecycle.setText(new StringBuffer().append(mTxtLifecycle.getText()).append("\n").append("onPause"));
        Log.d("JASON_CHIEN", "\nonPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mTxtLifecycle.setText(new StringBuffer().append(mTxtLifecycle.getText()).append("\n").append("onSaveInstanceState"));
        Log.d("JASON_CHIEN", "\nonSaveInstanceState");
    }

    @Override
    protected void onStop() {
        super.onStop();
        mTxtLifecycle.setText(new StringBuffer().append(mTxtLifecycle.getText()).append("\n").append("onStop"));
        Log.d("JASON_CHIEN", "\nonStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTxtLifecycle.setText(new StringBuffer().append(mTxtLifecycle.getText()).append("\n").append("onDestroy"));
        Log.d("JASON_CHIEN", "\nonDestroy");
    }
}
