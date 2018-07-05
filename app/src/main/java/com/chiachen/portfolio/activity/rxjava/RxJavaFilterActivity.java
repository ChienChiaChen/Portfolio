package com.chiachen.portfolio.activity.rxjava;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.activity.BaseActivity;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

// https://www.jianshu.com/p/b39afa92807e
public class RxJavaFilterActivity extends BaseActivity {

    TextView mRxOperatorsText;
            // .append("onNext : value : " + integer + "\n");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_create);
        mRxOperatorsText = findViewById(R.id.text);
        Observable.just(1, 20, 65, -5, 7, 19)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        return integer >= 10;
                        // buffer
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                mRxOperatorsText.append("filter : " + integer + "\n");
                logJJ("filter : " + integer + "\n");
            }
        });

    }
}
