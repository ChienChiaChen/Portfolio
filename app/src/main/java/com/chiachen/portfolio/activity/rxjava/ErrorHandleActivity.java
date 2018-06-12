package com.chiachen.portfolio.activity.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.chiachen.portfolio.R;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

//Ref.
//https://android.jlelse.eu/error-handling-in-rxjava-rxkotlin-e960300990e0
public class ErrorHandleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_handle);
    }

    public void onClickExceptionResumeNext(View view) {
        Observable
                .fromArray(1, 2, 3, 4)
                .doOnNext(new MyConsumer<Integer>())
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
                    @Override
                    public ObservableSource<? extends Integer> apply(Throwable throwable) throws Exception {
                        return Observable.just(10);
                        //One of the use cases of this can be a fall back mechanism.
                        //Suppose you want to get the latest news from an API and
                        //if there is an error (suppose network connection is down),
                        //you can plug in an observable that takes data from your local database.
                        //This way, you are still showing news (even though it is stale).
                    }
                })
                .subscribe(new MyObserver<Integer>());
    }

    public void onClickDoOnError(View view) {
        Observable.fromArray(1,2,3)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        if (2 == integer) {
                            throw new RuntimeException("Exception on 2");
                        }
                    }
                }).doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("Doing on error");
                        //showing toast message or snackbar informing about error.
                    }
                }).subscribe(new MyObserver<Integer>());

    }

    public void onClickErrorResumeNext(View view) {
    }

    public void onClickErrorReturnItem(View view) {
        Observable.fromArray(1, 2, 3)
                .doOnNext(new MyConsumer<Integer>())
                .onErrorReturnItem(-1)
                .subscribe(new MyObserver<Integer>());

    }

    public void onClickErrorReturn(View view) {
        Observable.fromArray(1, 2, 3)
                .doOnNext(new MyConsumer<Integer>())
                .onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable) throws Exception {
                        return 100;
                    }
                })
                .subscribe(new MyObserver<Integer>());

    }

    public void onClickRetry(View view) {
        Observable.fromArray(1, 2, 3)
                .doOnNext(new MyConsumer<Integer>())
                .retry(3)
                .onErrorReturnItem(-1)
                .subscribe(new MyObserver<Integer>());
    }

    static class MyObserver<T> implements Observer<T> {
        @Override
        public void onSubscribe(Disposable d) {
        }

        @Override
        public void onNext(T t) {
            System.out.println(t);
        }

        @Override
        public void onComplete() {
            System.out.println("onComplete");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println(e.getMessage());
        }
    }

    static class MyConsumer<T> implements Consumer<T> {
        @Override
        public void accept(T t) throws Exception {
            if (2 == (Integer) t) {
                throw new RuntimeException("Exception on 2");
            }
        }
    }
}
