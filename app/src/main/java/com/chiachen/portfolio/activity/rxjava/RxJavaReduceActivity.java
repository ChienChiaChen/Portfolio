package com.chiachen.portfolio.activity.rxjava;

import android.os.Bundle;
import android.widget.TextView;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.activity.BaseActivity;
import com.chiachen.portfolio.network.AppSchedulerProvider;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

// https://www.jianshu.com/p/b39afa92807e
public class RxJavaReduceActivity extends BaseActivity {

    TextView mRxOperatorsText;
            // .append("onNext : value : " + integer + "\n");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_create);
        mRxOperatorsText = findViewById(R.id.text);
        Observable.just(1, 2, 3)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer1, Integer integer2) throws Exception {
                        return integer1 + integer2;
                    }
                })
                .subscribeOn(AppSchedulerProvider.io())
                .observeOn(AppSchedulerProvider.ui())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        mRxOperatorsText.append("reduce : " + integer + "\n");
                        logJJ("accept: reduce : " + integer + "\n");
                    }
                });
    }
}
