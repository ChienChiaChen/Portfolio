package com.chiachen.portfolio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
}
