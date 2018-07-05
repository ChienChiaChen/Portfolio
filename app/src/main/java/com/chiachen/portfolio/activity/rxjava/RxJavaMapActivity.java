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
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

//https://www.jianshu.com/p/b39afa92807e
public class RxJavaMapActivity extends BaseActivity {

    TextView mRxOperatorsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_create);
        mRxOperatorsText = findViewById(R.id.text);
        firstExample();
        // secondExample();
    }

    private void firstExample() {
        Observable
                .fromArray(new String[]{"hi", "im", "jason"})
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s.toUpperCase();
                    }
                })
                .toList()
                .map(new Function<List<String>, List<String>>() {
                    @Override
                    public List<String> apply(List<String> list) throws Exception {
                        logJJ("Thread: " + Thread.currentThread().getName() + " " + "Reverse list");
                        Collections.reverse(list);
                        return list;
                    }
                })
                .subscribeOn(AppSchedulerProvider.io())
                .observeOn(AppSchedulerProvider.ui())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> list) throws Exception {
                        mRxOperatorsText.setText(list.toString());
                        logJJ("Thread: " + Thread.currentThread().getName() + " " + list.toString());
                    }
                });
    }

    private void secondExample() {
        Observable
                .just("http://www.yahoo.com/", "http://www.google.com/", "https://www.youtube.com/")
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s + " : " + getIPByUrl(s);
                    }
                })
                .subscribeOn(AppSchedulerProvider.io())
                .observeOn(AppSchedulerProvider.ui())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        logJJ(s);
                    }
                });
        // Observable.just(
        //         "http://www.baidu.com/",
        //         "http://www.google.com/",
        //         "https://www.bing.com/")
        //         .map(new Func1<String, String>() {
        //             @Override
        //             public String call(String s) {
        //                 try {
        //                     return s + " : " + getIPByUrl(s);
        //                 } catch (MalformedURLException e) {
        //                     e.printStackTrace();
        //                 } catch (UnknownHostException e) {
        //                     e.printStackTrace();
        //                 }
        //                 return null;
        //             }
        //         })
        //         .subscribeOn(Schedulers.io())
        //         .observeOn(AndroidSchedulers.mainThread())
        //         .subscribe(new Action1<String>() {
        //             @Override
        //             public void call(String s) {
        //                 printLog(tvLogs, "Consume Data: ", s);
        //             }
        //         });
    }

    private String getIPByUrl(String str) throws MalformedURLException, UnknownHostException {
        URL urls = new URL(str);
        String host = urls.getHost();
        String address = InetAddress.getByName(host).toString();
        int b = address.indexOf("/");
        return address.substring(b + 1);

    }
}
