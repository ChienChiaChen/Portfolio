package com.chiachen.portfolio.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.network.HttpMethods;
import com.chiachen.portfolio.network.Service.entity.Subject;
import com.chiachen.portfolio.network.subscribers.ProgressSubscriber;
import com.chiachen.portfolio.network.subscribers.SubscriberOnNextListener;

import java.util.List;

// Refer to: https://gank.io/post/56e80c2c677659311bed9841
public class RxJavaRetrofitActivity extends AppCompatActivity {

    private Button mClickMe;
    private TextView mResult;
    private SubscriberOnNextListener mOnNextListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_retrofit);
        mResult = findViewById(R.id.result_text);
        mClickMe = findViewById(R.id.click_me);

        mClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMovie();
            }
        });

        mOnNextListener = new SubscriberOnNextListener<List<Subject>>() {
            @Override
            public void onNext(List<Subject> subjects) {
                mResult.setText(subjects.toString());
            }
        };
    }

    public void getMovie() {
        HttpMethods.getInstance()
                .getTopMovie(0,10, new ProgressSubscriber<List<Subject>>(mOnNextListener,RxJavaRetrofitActivity.this));
    }
}
