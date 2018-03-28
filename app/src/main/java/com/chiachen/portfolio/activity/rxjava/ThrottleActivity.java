package com.chiachen.portfolio.activity.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.chiachen.portfolio.R;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

public class ThrottleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_throttle);

        RxView.clicks(findViewById(R.id.throttle_button))
                .throttleFirst(5000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Log.e("JASON_CHIEN", "\nOnClick" );
                    }
                });
    }
}
