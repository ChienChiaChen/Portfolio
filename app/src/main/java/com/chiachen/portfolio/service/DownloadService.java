package com.chiachen.portfolio.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import java.util.Random;

public class DownloadService extends Service {

    private final Random mRandom = new Random();
    private final Binder mLocalBinder = new LocalBinder();


    public class LocalBinder extends Binder {
        public DownloadService getService() {
            return DownloadService.this;
        }

        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Log.d("JASON_CHIEN", "\n從Activity 中取得的值-->>>>>" + data.readInt());
            Log.d("JASON_CHIEN", "\n從Activity 中取得的值-->>>>>" + data.readString());

            reply.writeInt(54);
            reply.writeString("Jason HI~~");
            return super.onTransact(code, data, reply, flags);
        }
    }

    public DownloadService() {
    }

    public int getRandom() {
        return mRandom.nextInt(100);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("JASON_CHIEN", "\nonBind");
        return mLocalBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("JASON_CHIEN", "\nonCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("JASON_CHIEN", "\nonCreate");

    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("JASON_CHIEN", "\nonUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d("JASON_CHIEN", "\nonRebind");
    }
}
