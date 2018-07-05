package com.chiachen.portfolio.activity.rxjava;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.activity.BaseActivity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

// https://www.jianshu.com/p/b39afa92807e
public class RxJavaBufferActivity extends BaseActivity {

    TextView mRxOperatorsText;
            // .append("onNext : value : " + integer + "\n");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_create);
        mRxOperatorsText = findViewById(R.id.text);
        Observable.just(1, 2, 3, 4, 5)
                .buffer(3, 2)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(@NonNull List<Integer> integers) throws Exception {
                        mRxOperatorsText.append("buffer size : " + integers.size() + "\n");
                        logJJ("buffer size : " + integers.size() + "\n");
                        mRxOperatorsText.append("buffer value : ");
                        logJJ("buffer value : " );
                        for (Integer i : integers) {
                            mRxOperatorsText.append(i + "");
                            logJJ(i + "");
                        }
                        mRxOperatorsText.append("\n");
                        logJJ( "\n");
                    }
                });

    }
}
