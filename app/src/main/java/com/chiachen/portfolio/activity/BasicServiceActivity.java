package com.chiachen.portfolio.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.service.BasicService;

public class BasicServiceActivity extends AppCompatActivity {

    private BasicService.MyBinder myBinder;

    private ServiceConnection mServiceConnection =new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("JASON_CHIEN", "\nonServiceConnected");
            myBinder = (BasicService.MyBinder) service;
            myBinder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

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

    public void bindService_(View view) {
        bindService(new Intent(BasicServiceActivity.this, BasicService.class), mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    public void UnbindService(View view) {
        unbindService(mServiceConnection);
    }
}
