package com.chiachen.portfolio.activity.rxjava;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.activity.BaseActivity;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

// https://www.jianshu.com/p/b39afa92807e
public class RxJavaConcatActivity extends BaseActivity {

    TextView mRxOperatorsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_create);
        mRxOperatorsText = findViewById(R.id.text);
        Observable.concat(Observable.just(1,2,3), Observable.just(4,5,6))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        mRxOperatorsText.append("concat : "+ integer + "\n");
                        logJJ( "concat : "+ integer + "\n" );
                    }
                });
    }
}
