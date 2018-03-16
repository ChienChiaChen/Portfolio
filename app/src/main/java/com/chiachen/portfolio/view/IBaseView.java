package com.chiachen.portfolio.view;

/**
 * Created by jianjiacheng on 03/03/2018.
 */

public interface IBaseView {
    void showLoading();
    void hideLoading();
    void onUnknownError(String error);
    void onTimeout();
    void onNetworkError();
    boolean isNetworkConnected();
    void onConnectionError();
}
