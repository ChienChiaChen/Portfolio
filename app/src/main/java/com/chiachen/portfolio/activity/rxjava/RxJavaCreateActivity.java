package com.chiachen.portfolio.activity.rxjava;

import android.os.Bundle;
import android.widget.TextView;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.activity.BaseActivity;
import com.chiachen.portfolio.network.AppSchedulerProvider;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

// https://www.jianshu.com/p/b39afa92807e
public class RxJavaCreateActivity extends BaseActivity {

    TextView mRxOperatorsText;
            // .append("onNext : value : " + integer + "\n");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_create);
        mRxOperatorsText = findViewById(R.id.text);
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                    for (int i = 0; i < 5; i++) {
                        logJJ(Thread.currentThread().getName());
                        e.onNext(String.valueOf(i));
                    }
                }
            })
            .subscribeOn(AppSchedulerProvider.io())
            .observeOn(AppSchedulerProvider.ui())
            .subscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Exception {
                    mRxOperatorsText.setText(String.format("%sonNext : value : %s\n", mRxOperatorsText.getText().toString(), s));
                    logJJ(s + "  " + Thread.currentThread().getName());
                }
            });
    }
}
