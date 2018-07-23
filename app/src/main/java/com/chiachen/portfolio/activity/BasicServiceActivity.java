package com.chiachen.portfolio.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.aidl.IMyAidlInterface;
import com.chiachen.portfolio.service.BasicService;

public class BasicServiceActivity extends AppCompatActivity {

    private BasicService.MyBinder myBinder;

    private IMyAidlInterface mIMyAidlInterface;

    private ServiceConnection mServiceConnection =new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("JASON_CHIEN", "\nonServiceConnected");
            mIMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            try {
                int result = mIMyAidlInterface.plus(3, 5);
                String upperStr = mIMyAidlInterface.toUpperCase("hello world");
                Log.d("JASON_CHIEN", "result is " + result);
                Log.d("JASON_CHIEN", "upperStr is " + upperStr);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_service);
        Log.d("JASON_CHIEN", "\nBasicServiceActivity: myId"+ Process.myPid());
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
