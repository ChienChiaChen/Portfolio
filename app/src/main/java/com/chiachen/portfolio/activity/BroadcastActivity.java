package com.chiachen.portfolio.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chiachen.portfolio.R;

public class BroadcastActivity extends AppCompatActivity {
    public final static String SHOW_DIALOG = "Show_Dialog";
    public final static String SHOW_NOTIFICATION = "SHOW_NOTIFICATION";

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (SHOW_DIALOG.equals(action)) {
                showAlertDialog();
            } else if (SHOW_NOTIFICATION.equals(action)) {
                showNotification(context);
            }
        }

        private void showNotification(Context context) {
            NotificationManager noMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (null == noMgr) {
                return;
            }

            Intent call = new Intent(context, MainActivity.class);
            call.putExtra("notiId", 1);
            PendingIntent pIntent = PendingIntent.getActivity(context, 0, call, PendingIntent.FLAG_NO_CREATE);

            Notification notification = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Testing")
                    .setContentText("TT")
                    .setLights(Color.RED, 1000, 1000)
                    .setVibrate(new long[]{0, 400, 250, 400})
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setAutoCancel(true)
                    .setContentIntent(pIntent)
                    .build();

            noMgr.notify(1, notification);
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

        findViewById(R.id.show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerReceiver(mBroadcastReceiver, new IntentFilter(SHOW_DIALOG));
                Intent intent = new Intent();
                intent.setAction(SHOW_DIALOG);
                sendBroadcast(intent);
            }
        });

        findViewById(R.id.show_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerReceiver(mBroadcastReceiver, new IntentFilter(SHOW_NOTIFICATION));
                Intent intent = new Intent();
                intent.setAction(SHOW_NOTIFICATION);
                sendBroadcast(intent);
            }
        });
    }
}
