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
    private CallBack mCallback;

    public CallBack getCallback() {
        return mCallback;
    }

    public MyService setCallback(CallBack callback) {
        mCallback = callback;
        return this;
    }

    public class MyBinder extends Binder {
        private String mData;

        public MyService getService(){
            return MyService.this;
        }

        public void setData(String data) {
            mData = data;
        }
    }

    public static interface CallBack {
        void onShowChanged(String show);
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
        Log.e("JASON_CHIEN", "\nIn onCreate thread: " + Thread.currentThread().getName() + "\n, id: " + Thread.currentThread().getId());
        new Thread(){
            @Override
            public void run() {
                super.run();
                Log.e("JASON_CHIEN", "\nIn another thread: " + Thread.currentThread().getName() + "\n, id: " + Thread.currentThread().getId());
                int i = 0;
                while (30 != i) {
                    if (null != getCallback()) {
                        getCallback().onShowChanged("" + i++);
                    }

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
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
