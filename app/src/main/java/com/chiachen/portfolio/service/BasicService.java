package com.chiachen.portfolio.service;


import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.activity.BasicServiceActivity;
import com.chiachen.portfolio.aidl.IMyAidlInterface;

public class BasicService extends Service {

    private MyBinder mBinderr = new MyBinder();

    public BasicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("JASON_CHIEN", "\nBasicService: myId"+ Process.myPid());
        Log.d("JASON_CHIEN", "\nonCreate");
        // try {
        //     Thread.sleep(60000);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Notification.Builder builder = new Notification.Builder(this.getApplicationContext());
        Intent nfIntent = new Intent(this, BasicServiceActivity.class);

        builder.setContentIntent(PendingIntent.getActivity(this, 0, nfIntent, 0))
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher))
                .setContentTitle("下拉列表中的Title")// Content title.
                .setContentText("要顯示的內容") // Content text.
                .setSmallIcon(R.mipmap.ic_launcher) // Small icon.
                .setWhen(System.currentTimeMillis()); // Set time.

        Notification notification = builder.build();
        notification.defaults = Notification.DEFAULT_SOUND;

        startForeground(110, notification);// start foreground service

        Log.d("JASON_CHIEN", "\nonStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("JASON_CHIEN", "\nonDestroy");
        stopForeground(true);//Stop foreground service.
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("JASON_CHIEN", "\nonBind");
        return mBinder;
    }

    public class MyBinder extends Binder {
        public void startDownload() {
            Log.d("JASON_CHIEN", "\nstartDownload() executed");
        }
    }

    IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {

        @Override
        public String toUpperCase(String str) throws RemoteException {
            if (str != null) {
                return str.toUpperCase();
            }
            return null;
        }

        @Override
        public int plus(int a, int b) throws RemoteException {
            return a + b;
        }
    };

}
