package com.chiachen.portfolio.activity.rxjava;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.activity.BaseActivity;
import com.chiachen.portfolio.utils.TimeUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

// https://www.jianshu.com/p/b39afa92807e
public class RxJavaIntervalActivity extends BaseActivity {

    private TextView mRxOperatorsText;
    private Disposable disposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_create);
        mRxOperatorsText = findViewById(R.id.text);
        mRxOperatorsText.append("timer start : " + TimeUtil.getNowStrTime() + "\n");
        logJJ( "timer start : " + TimeUtil.getNowStrTime() + "\n");
        disposable = Observable.interval(3,2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // 由于interval默认在新线程，所以我们应该切回主线程
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        mRxOperatorsText.append("interval :" + aLong + " at " + TimeUtil.getNowStrTime() + "\n");
                        logJJ("interval :" + aLong + " at " + TimeUtil.getNowStrTime() + "\n");
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
