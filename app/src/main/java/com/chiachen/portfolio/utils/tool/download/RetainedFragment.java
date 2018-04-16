package com.chiachen.portfolio.utils.tool.download;

import android.app.Fragment;
import android.os.Bundle;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 *
 *  For storing progress
 *
 * @author wangchenlong
 */
public class RetainedFragment extends Fragment {
    private CustomAsyncTask mCustomAsyncTask;
    private Observable<Long> mObservable;
    private PublishSubject<Long> mSubject;

    private int mMode;
    private boolean mBusy;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public CustomAsyncTask getCustomAsyncTask() {
        return mCustomAsyncTask;
    }

    public void setCustomAsyncTask(CustomAsyncTask customAsyncTask) {
        mCustomAsyncTask = customAsyncTask;
    }

    public Observable<Long> getObservable() {
        return mObservable;
    }

    public void setObservable(Observable<Long> observable) {
        mObservable = observable;
    }

    public PublishSubject<Long> getSubject() {
        return mSubject;
    }

    public void setSubject(PublishSubject<Long> subject) {
        mSubject = subject;
    }

    public int getMode() {
        return mMode;
    }

    public void setMode(int mode) {
        mMode = mode;
    }

    public boolean isBusy() {
        return mBusy;
    }

    public void setBusy(boolean busy) {
        mBusy = busy;
    }
}
