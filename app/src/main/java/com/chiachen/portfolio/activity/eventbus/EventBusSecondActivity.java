package com.chiachen.portfolio.activity.eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.chiachen.portfolio.R;

import org.greenrobot.eventbus.EventBus;

public class EventBusSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_second);
    }

    public void SendEventToMainPage(View view) {
        EventBus.getDefault().post(new Event("You got it"));
    }
}
