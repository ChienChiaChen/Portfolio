package com.chiachen.portfolio.activity.coordinator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chiachen.portfolio.R;

public class CoordinatorPracticeMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_practice);
    }

    public void openCoordinator1(View view) {
        startActivity(new Intent(CoordinatorPracticeMainActivity.this, CoordinatorPractice1Activity.class));
    }

    public void openCoordinator2(View view) {
        startActivity(new Intent(CoordinatorPracticeMainActivity.this, CoordinatorPractice2Activity.class));
    }

    public void openCoordinator3(View view) {
        startActivity(new Intent(CoordinatorPracticeMainActivity.this, CoordinatorPractice3Activity.class));
    }
}
