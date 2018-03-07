package com.chiachen.portfolio.network.subscribers;

import android.content.Context;

import com.chiachen.portfolio.utils.progress.ProgressCancelListener;
import com.chiachen.portfolio.utils.progress.ProgressDialogHandler;

import io.reactivex.observers.ResourceObserver;

/**
 * Created by jianjiacheng on 07/03/2018.
 */

public class ProgressSubscriber<T> extends ResourceObserver<T> implements ProgressCancelListener{
    private SubscriberOnNextListener mNextListener;
    private Context mContext;
    private ProgressDialogHandler mProgressDialogHandler;

    public ProgressSubscriber(Context context, SubscriberOnNextListener nextListener) {
        mContext = context;
        mNextListener = nextListener;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
    }

    @Override
    protected void onStart() {
        showProgressDialog();
    }

    @Override
    public void onNext(T t) {
        mNextListener.onNext(t);
    }

    @Override
    public void onError(Throwable t) {
        dismissProgressDialog();
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
    }

    @Override
    public void onCancelProgress() {
        if (!this.isDisposed()) {
            this.dispose();
        }
    }

    private void showProgressDialog() {
        if (null != mProgressDialogHandler) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (null != mProgressDialogHandler) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }
}
