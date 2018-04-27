package com.chiachen.portfolio.network.subscribers;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * Created by jianjiacheng on 27/04/2018.
 */

public abstract class _ApiCallback<M> extends DisposableObserver<M> {
    public static final int NETWORK_ERR = 504;
    public static final int SERVER_ERR_1 = 502;
    public static final int SERVER_ERR_2 = 404;

    public abstract void onSuccess(M model);
    public abstract void onFailure(String msg);
    public abstract void onFinish();

    @Override
    public void onNext(M m) {
        onSuccess(m);
    }

    @Override
    public void onComplete() {
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;

            int errCode = httpException.code();
            String errMsg = httpException.message();

            if (NETWORK_ERR == errCode) {
                errMsg = "Network error";
            } else if (SERVER_ERR_1 == errCode || SERVER_ERR_2 == errCode) {
                errMsg = "Server error";
            }

            onFailure(errMsg);
        } else {
            onFailure(e.getMessage());
        }

        onFinish();
    }
}
