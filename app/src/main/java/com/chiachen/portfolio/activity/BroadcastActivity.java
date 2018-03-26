package com.chiachen.portfolio.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chiachen.portfolio.R;

public class BroadcastActivity extends AppCompatActivity {
    private final static String MY_MESSAGE = "com.givemepass.sendmessage";

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        private final static String MY_MESSAGE = "com.givemepass.sendmessage";
        @Override
        public void onReceive(Context context, Intent intent) {
            if (MY_MESSAGE.equals(intent.getAction())) {
                showAlertDialog();
            }
        }

        private void showAlertDialog() {
            new AlertDialog.Builder(BroadcastActivity.this).setMessage("Got it").setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    unregisterReceiver(mBroadcastReceiver);
                }
            }).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        initUI();
    }

    private void initUI() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (null != toolbar) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(getString(R.string.app_name));
        }

        findViewById(R.id.make_broadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerReceiver(mBroadcastReceiver, new IntentFilter(MY_MESSAGE));
                Intent intent = new Intent();
                intent.setAction(MY_MESSAGE);
                sendBroadcast(intent);
            }
        });
    }
}
