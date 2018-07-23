package com.chiachen.portfolio.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.service.DownloadService;

public class InteractServiceActivity extends AppCompatActivity {

    private boolean mFlag;
    private DownloadService mDownloadService;
    private DownloadService.LocalBinder mLocalBinder;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bind_service: {
                    // bind service
                    Intent intent = new Intent(InteractServiceActivity.this, DownloadService.class);
                    bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
                    break;
                }

                case R.id.invoke_service_methods: {
                    if (mFlag) {
                        int value = mDownloadService.getRandom();
                        Toast.makeText(v.getContext(), new StringBuilder("Hi~ ").append(value), Toast.LENGTH_SHORT).show();
                    }
                    break;
                }

                case R.id.unbind_service: {
                    unbindService(mServiceConnection);
                    mFlag = false;
                    break;
                }

                case R.id.interact_with_service: {
                    // 向Service 傳遞數據
                    Parcel parcel = Parcel.obtain();
                    parcel.writeInt(10);
                    parcel.writeString("Jason");

                    // 從Service 中獲取資料
                    Parcel reply = Parcel.obtain();
                    try {
                        mLocalBinder.transact(IBinder.LAST_CALL_TRANSACTION, parcel, reply, 0);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    Log.d("JASON_CHIEN", "\n從Service 中回傳的值-->>>>>" + reply.readInt());
                    Log.d("JASON_CHIEN", "\n從Service 中回傳的值-->>>>>" + reply.readString());
                    break;
                }
            }

        }
    };

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mLocalBinder = (DownloadService.LocalBinder) service;
            mDownloadService = mLocalBinder.getService();
            mFlag = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mFlag = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interact_service);

        findViewById(R.id.bind_service).setOnClickListener(mOnClickListener);
        findViewById(R.id.invoke_service_methods).setOnClickListener(mOnClickListener);
        findViewById(R.id.unbind_service).setOnClickListener(mOnClickListener);
        findViewById(R.id.interact_with_service).setOnClickListener(mOnClickListener);

    }
}
