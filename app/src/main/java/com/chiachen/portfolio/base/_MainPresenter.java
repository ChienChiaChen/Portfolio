package com.chiachen.portfolio.base;

import com.chiachen.portfolio.network.RetrofitFactory;
import com.chiachen.portfolio.network.response.Repo;
import com.chiachen.portfolio.network.service.GitHubService;
import com.chiachen.portfolio.network.subscribers._ApiCallback;

import java.util.ArrayList;

/**
 * Created by jianjiacheng on 27/04/2018.
 */

public class _MainPresenter extends _BasePresenter<_MainView> {

    public _MainPresenter(_MainView mainView) {
        attachView(mainView);
    }


    public void loadData(String username) {
        if (isViewAttached()) {
            getView().showLoading();
        }

        addSubscription(RetrofitFactory.getInstance().create(GitHubService.class).getRepoData(username)
                , new _ApiCallback<ArrayList<Repo>>() {
                    @Override
                    public void onSuccess(ArrayList<Repo> model) {
                        if (isViewAttached()) {
                            getView().getDataSuccess(model);
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        if (isViewAttached()) {
                            getView().getDataFail(msg);
                        }
                    }

                    @Override
                    public void onFinish() {
                        if (isViewAttached()) {
                            getView().hideLoading();
                        }
                    }
                }
        );
    }
}
