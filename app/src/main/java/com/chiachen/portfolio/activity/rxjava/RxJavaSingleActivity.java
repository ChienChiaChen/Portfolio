package com.chiachen.portfolio.activity.rxjava;

import android.os.Bundle;
import android.widget.TextView;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.activity.BaseActivity;

import java.util.Random;

import io.reactivex.Single;
import io.reactivex.functions.Consumer;

// https://www.jianshu.com/p/b39afa92807e
public class RxJavaSingleActivity extends BaseActivity {

    TextView mRxOperatorsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_create);
        mRxOperatorsText = findViewById(R.id.text);

        Single.just(new Random().nextInt())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        mRxOperatorsText.append("single : onSuccess : "+integer+"\n");
                        logJJ("single : onSuccess : "+integer+"\n");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mRxOperatorsText.append("single : onError : "+throwable.getMessage()+"\n");
                        logJJ("single : onError : "+throwable.getMessage()+"\n");
                    }
                });
    }
}
