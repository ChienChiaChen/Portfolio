package com.chiachen.portfolio.presenter;

import com.chiachen.portfolio.data.InfoRepository;
import com.chiachen.portfolio.data.UserRepository;
import com.chiachen.portfolio.global.ResourceService;
import com.chiachen.portfolio.network.AppSchedulerProvider;
import com.chiachen.portfolio.network.RetrofitFactory;
import com.chiachen.portfolio.network.SchedulersTransformer;
import com.chiachen.portfolio.network.service.LocalLoginService;
import com.chiachen.portfolio.network.subscribers.ProgressSubscriber;
import com.chiachen.portfolio.network.subscribers.SubscriberOnNextListener;
import com.chiachen.portfolio.utils.tool.PreferenceHelper;
import com.chiachen.portfolio.view.ILoginView;

import java.util.List;

import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by jianjiacheng on 15/04/2018.
 */

public class LoginPresenter extends BasePresenter<InfoRepository, ILoginView> {

    @Override
    protected void updateView() {
        getView().loginSuccessfully();
    }

    public void requestLogin(String name, String pwd) {
        RetrofitFactory.getInstance()
                .create(LocalLoginService.class)
                .requestLogin(name, pwd)
                .subscribeOn(AppSchedulerProvider.io())
                .doOnNext(new Consumer<List<UserRepository>>() {
                    @Override
                    public void accept(List<UserRepository> userRepositories) throws Exception {
                        PreferenceHelper.onSetPrefString("Login", "name", userRepositories.get(0).name, ResourceService.getCurrentPageContext());
                        PreferenceHelper.onSetPrefString("Login", "pwd", userRepositories.get(0).name, ResourceService.getCurrentPageContext());
                    }
                })
                .observeOn(AppSchedulerProvider.io())
                .flatMap(new Function<List<UserRepository>, ObservableSource<List<InfoRepository>>>() {
                    @Override
                    public ObservableSource<List<InfoRepository>> apply(List<UserRepository> userRepositories) throws Exception {
                        return RetrofitFactory.getInstance()
                                        .create(LocalLoginService.class)
                                        .getInfo(userRepositories.get(0).id);
                    }
                })
                .doOnNext(new Consumer<List<InfoRepository>>() {
                    @Override
                    public void accept(List<InfoRepository> infoRepository) throws Exception {
                        PreferenceHelper.onSetPrefString("Login", "height",infoRepository.get(0).height, ResourceService.getCurrentPageContext());
                        PreferenceHelper.onSetPrefString("Login", "weight",infoRepository.get(0).weight, ResourceService.getCurrentPageContext());
                    }
                })
                .compose(SchedulersTransformer.<List<InfoRepository>>ioToMain())
                .subscribe(new ProgressSubscriber<>(getView(), ResourceService.getCurrentPageContext(), new SubscriberOnNextListener<List<InfoRepository>>() {
                    @Override
                    public void onNext(List<InfoRepository> infoRepositories) {
                        setModel(infoRepositories.get(0));
                    }
                }));
    }
}
