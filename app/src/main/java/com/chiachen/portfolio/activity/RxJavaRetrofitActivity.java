package com.chiachen.portfolio.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.network.AppSchedulerProvider;
import com.chiachen.portfolio.network.RetrofitFactory;
import com.chiachen.portfolio.network.response.Repo;
import com.chiachen.portfolio.network.service.GitHubService;
import com.chiachen.portfolio.network.subscribers.ProgressSubscriber;
import com.chiachen.portfolio.network.subscribers.SubscriberOnNextListener;

import java.util.ArrayList;


// Refer to: https://gank.io/post/56e80c2c677659311bed9841
public class RxJavaRetrofitActivity extends BaseActivity {

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

        mOnNextListener = new SubscriberOnNextListener<ArrayList<Repo>>() {
            @Override
            public void onNext(ArrayList<Repo> subjects) {
                StringBuilder stringBuffer = new StringBuilder();
                for (Repo subject : subjects) {
                    stringBuffer.append(subject.language);
                    stringBuffer.append("\n");
                }
                mResult.setText(stringBuffer.toString());
            }
        };
    }

    public void getMovie() {
        RetrofitFactory.getInstance().create(GitHubService.class)
                .getRepoData("JasonChienPrenetics")
                .subscribeOn(AppSchedulerProvider.io())
                .observeOn(AppSchedulerProvider.ui())
                .subscribe(new ProgressSubscriber<ArrayList<Repo>>(this, this, mOnNextListener));

    }
}
