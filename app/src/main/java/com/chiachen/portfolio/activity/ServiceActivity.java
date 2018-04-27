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
import com.chiachen.portfolio.service.MyService;

public class ServiceActivity extends AppCompatActivity {
    public static final String TAG = "Jason_Chien";
    private MyService mService;

    public ServiceConnection connection = new ServiceConnection() {

        // 成功與 Service 建立連線
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = ((MyService.MyBinder) service).getService();
            if (null != mService) {
                mService.setCallback(new MyService.CallBack() {
                    @Override
                    public void onShowChanged(String show) {
                        Log.e("JASON_CHIEN", "\n" + show);
                    }
                });
            }
            Log.d(TAG, "ServiceActivity onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            Log.d(TAG, "ServiceActivity onServiceFailed");
        }
    };

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button1: {
                    Intent serviceIntent = new Intent(ServiceActivity.this, MyService.class);
                    ServiceActivity.this.bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
                    break;
                }

                case R.id.button2: {
                    if (null == mService) break;
                    mService.test();
                    break;
                }

                case R.id.button3: {
                    try {
                        ServiceActivity.this.unbindService(connection);
                    } catch (Exception e) {
                        Log.e("JASON_CHIEN", "\n" + e.getMessage());
                    }
                    break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        findViewById(R.id.button1).setOnClickListener(mOnClickListener);
        findViewById(R.id.button2).setOnClickListener(mOnClickListener);
        findViewById(R.id.button3).setOnClickListener(mOnClickListener);
    }
}
