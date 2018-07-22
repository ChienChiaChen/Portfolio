package com.chiachen.portfolio.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class BasicService extends Service {

    public BasicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("JASON_CHIEN", "\nonCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("JASON_CHIEN", "\nonStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("JASON_CHIEN", "\nonDestroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
