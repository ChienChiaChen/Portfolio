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
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

// https://www.jianshu.com/p/b39afa92807e
public class RxJavaTimerActivity extends BaseActivity {

    TextView mRxOperatorsText;
            // .append("onNext : value : " + integer + "\n");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_create);
        mRxOperatorsText = findViewById(R.id.text);
        mRxOperatorsText.append("timer start : " + TimeUtil.getNowStrTime() + "\n");
        logJJ( "timer start : " + TimeUtil.getNowStrTime() + "\n");
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // timer 默认在新线程，所以需要切换回主线程
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        mRxOperatorsText.append("timer :" + aLong + " at " + TimeUtil.getNowStrTime() + "\n");
                        logJJ("timer :" + aLong + " at " + TimeUtil.getNowStrTime() + "\n");
                    }
                });

    }
}
