package com.chiachen.portfolio.network;


import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AppSchedulerProvider {

    public static Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    public static Scheduler computation() {
        return Schedulers.computation();
    }

    public static Scheduler io() {
        return Schedulers.io();
    }
}
