package com.chiachen.portfolio.activity.rxjava;

import android.os.Bundle;

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

public class RxJavaConcatMapActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_concat_map);
        Observable.just(
                "http://www.yahoo.com/",
                "http://www.google.com/",
                "http://www.youtube.com")
                .concatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        return createIpObservableMultiThread(s);
                    }
                })
                .subscribeOn(AppSchedulerProvider.io())
                .observeOn(AppSchedulerProvider.ui())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String ip) throws Exception {
                        logJJ(ip + "\n" + Thread.currentThread().getName());
                    }
                });
    }

    private synchronized ObservableSource<String> createIpObservableMultiThread(final String url) {
        return Observable
                .create(new ObservableOnSubscribe<String>() {
                        @Override
                        public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                            try {
                                logJJ(url+"\n"+Thread.currentThread().getName());
                                String ip = getIPByUrl(url);
                                observableEmitter.onNext(ip);
                            } catch (MalformedURLException e) {
                                observableEmitter.onNext(null);
                            } catch (UnknownHostException e) {
                                observableEmitter.onNext(null);
                            }
                            observableEmitter.onComplete();
                        }
                    })
                .subscribeOn(AppSchedulerProvider.io());
    }

    private String getIPByUrl(String str) throws MalformedURLException, UnknownHostException {
        URL urls = new URL(str);
        String host = urls.getHost();
        String address = InetAddress.getByName(host).toString();
        int b = address.indexOf("/");
        return address.substring(b + 1);
    }
}
