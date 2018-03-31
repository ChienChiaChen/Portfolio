package com.chiachen.portfolio.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by jianjiacheng on 31/03/2018.
 */

public class MyService extends Service {
    public MyBinder mBinder = new MyBinder();

    public class MyBinder extends Binder {
        public MyService getService(){
            return MyService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("JASON_CHIEN", "\nonBind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("JASON_CHIEN", "\nonUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        Log.e("JASON_CHIEN", "\nonCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("JASON_CHIEN", "\nonStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e("JASON_CHIEN", "\nonDestroy");
        super.onDestroy();
    }

    public void test() {
        Log.e("JASON_CHIEN", "\nTest");
    }
}
