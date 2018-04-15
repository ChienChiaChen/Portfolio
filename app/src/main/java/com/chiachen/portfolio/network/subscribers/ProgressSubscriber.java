package com.chiachen.portfolio.network.subscribers;

import android.content.Context;

import com.chiachen.portfolio.network.progress.ProgressCancelListener;
import com.chiachen.portfolio.network.progress.ProgressDialogHandler;
import com.chiachen.portfolio.view.IBaseView;

import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by jianjiacheng on 16/03/2018.
 */

//https://www.jianshu.com/p/bd758f51742e
public class ProgressSubscriber<T> extends DisposableObserver<T> implements ProgressCancelListener {
    private SubscriberOnNextListener<T> mNextListener;
    private ProgressDialogHandler mProgressHandler;
    private IBaseView mView;

    public ProgressSubscriber(IBaseView baseView, Context context, SubscriberOnNextListener<T> nextListener) {
        mView = baseView;
        mNextListener = nextListener;
        mProgressHandler = new ProgressDialogHandler(context, this,true);
    }

    @Override
    protected void onStart() {
        showProgressDialog();
    }

    @Override
    public void onNext(T t) {
        if (null != mNextListener) {
            mNextListener.onNext(t);
        }
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
    }

    @Override
    public void onError(Throwable throwable) {
        if (throwable instanceof HttpException) {
            ResponseBody responseBody = ((HttpException) throwable).response().errorBody();
            mView.onUnknownError(getErrorMsg(responseBody));
        } else if (throwable instanceof SocketException) {
            mView.onTimeout();
        } else if (throwable instanceof IOException) {
            mView.onNetworkError();
        } else {
            mView.onUnknownError(throwable.getMessage());
        }

        dismissProgressDialog();
    }

    @Override
    public void onCancelProgress() {
        if (!this.isDisposed()) {
            this.dispose();
        }
    }

    private void showProgressDialog() {
        if (null != mProgressHandler) {
            mProgressHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }
    private void dismissProgressDialog() {
        if (null != mProgressHandler) {
            mProgressHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressHandler = null;
        }
    }

    private String getErrorMsg(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.toString());
            return jsonObject.getString("message");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
