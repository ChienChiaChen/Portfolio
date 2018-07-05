package com.chiachen.portfolio.activity.rxjava;

import android.os.Bundle;
import android.widget.TextView;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.activity.BaseActivity;
import com.chiachen.portfolio.network.AppSchedulerProvider;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RxJavaFlatMapActivity extends BaseActivity {
    TextView mRxOperatorsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_create);
        mRxOperatorsText = findViewById(R.id.text);
        Observable.just("http://www.yahoo.com/", "http://www.google.com/", "https://www.youtube.com/")
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(final String s) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<String>() {
                            @Override
                            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                                try {
                                    logJJ(s+"\n"+Thread.currentThread().getName());
                                    String ip = getIPByUrl(s);
                                    observableEmitter.onNext(ip);
                                } catch (MalformedURLException e) {
                                    observableEmitter.onNext(null);
                                } catch (UnknownHostException e) {
                                    observableEmitter.onNext(null);
                                }
                                observableEmitter.onComplete();
                            }
                        })
                        .subscribeOn(AppSchedulerProvider.io());// <--- on diff thread.
                    }
                })
                .observeOn(AppSchedulerProvider.ui())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mRxOperatorsText.setText(String.format("%sAccept : value : %s\n", mRxOperatorsText.getText().toString(), s));
                        logJJ(s);
                    }
                });
    }

    private String getIPByUrl(String str) throws MalformedURLException, UnknownHostException {
        URL urls = new URL(str);
        String host = urls.getHost();
        String address = InetAddress.getByName(host).toString();
        int b = address.indexOf("/");
        return address.substring(b + 1);

    }
}
