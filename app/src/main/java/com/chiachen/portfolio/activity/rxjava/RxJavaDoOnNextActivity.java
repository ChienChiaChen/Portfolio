package com.chiachen.portfolio.activity.rxjava;

import android.os.Bundle;
import android.widget.TextView;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.activity.BaseActivity;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

// https://www.jianshu.com/p/b39afa92807e
public class RxJavaDoOnNextActivity extends BaseActivity {

    TextView mRxOperatorsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_create);
        mRxOperatorsText = findViewById(R.id.text);
        Observable
                .just(1,2,3,4)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        mRxOperatorsText.append("doOnNext 保存 " + integer + "成功" + "\n");
                        logJJ("doOnNext 保存 " + integer + "成功" + "\n");
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        mRxOperatorsText.append("doOnNext :" + integer + "\n");
                        logJJ("doOnNext :" + integer + "\n");
                    }
                });
    }
}
