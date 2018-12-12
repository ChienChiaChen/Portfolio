package com.chiachen.portfolio.activity.rxjava.backpresure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.network.AppSchedulerProvider;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BackpressureExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backpressure_example);
    }

    public void lesson1(View view) {
        //被觀察者發送速度>觀察者接收速度
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; ; i++) {
                    Log.e("Jason", ".subscribe (line 30): " + i);
                    Thread.sleep(10); // ONE PER 10 MS
                    emitter.onNext(i);

                }
            }
        })
                .subscribeOn(AppSchedulerProvider.io())
                .observeOn(AppSchedulerProvider.ui())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("Jason", ".onSubscribe (line 36): Start!!");

                    }

                    @Override
                    public void onNext(Integer integer) {
                        try {
                            Thread.sleep(5000);// ONE PER 5 SECONDS
                            Log.e("Jason", ".onNext (line 45): " + integer);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Jason", ".onError (line 53): ");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("Jason", ".onComplete (line 58): ");
                    }
                });
    }

    public void lesson2(View view) {
        Flowable<Integer> upstream = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();

            }
        }, BackpressureStrategy.ERROR);

        Subscriber<Integer> downstream = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.e("Jason", ".onSubscribe (line 92): ");
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                Log.e("Jason", ".onNext (line 97): ");
            }

            @Override
            public void onError(Throwable t) {
                Log.e("Jason", ".onError (line 102): ");
            }

            @Override
            public void onComplete() {
                Log.e("Jason", ".onComplete (line 107): ");
            }
        };

        upstream.subscribe(downstream);
    }

    Subscription subscription;

    public void lesson3Emit(View view) {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onComplete();
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(AppSchedulerProvider.io())
                .observeOn(AppSchedulerProvider.ui())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        subscription = s;
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e("Jason", ".onNext (line 132): " + integer);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {
                        Log.e("Jason", ".onComplete (line 144): ");
                    }
                });
    }

    public void lesson3Handle(View view) {
        if (subscription != null) {
            subscription.request(48);
//            subscription.request(2);
        }
    }

    public void lesson4(View view) {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 129; i++) {
                    emitter.onNext(i);
                }

                emitter.onComplete();
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(AppSchedulerProvider.io())
                .observeOn(AppSchedulerProvider.ui())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e("Jason", ".onNext (line 178): " + integer);

                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e("Jason", ".onError (line 185): " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void Sync(View view) {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {

                long n = emitter.requested();
                Log.e("Jason", ".subscribe 可接收事件 (line 204): " + n);
                for (int i = 0; i < n; i++) {
                    Log.e("Jason", ".subscribe (line 207): " + i);
                    emitter.onNext(i);
                }
            }
        }, BackpressureStrategy.ERROR)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(10);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e("Jason", ".onNext (line 225): " + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e("Jason", ".onError (line 229): ", t);
                    }

                    @Override
                    public void onComplete() {
                        Log.e("Jason", ".onComplete (line 234): ");
                    }
                });
    }

    public void Async(View view) {

        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {

                Log.e("Jason", ".subscribe (line 238): 可接收事件数量 = " + emitter.requested());

                boolean flag;

                // 發送500个事件
                for (int i = 0; i < 500; i++) {
                    flag = false;

                    // 若requested() == 0则不发送
                    while (emitter.requested() == 0) {
                        if (!flag) {
                            Log.e("Jason", ".subscribe (line 248): 不送了");
                            flag = true;
                        }
                    }
                    // requested() ≠ 0 才发送
                    Log.e("Jason", ".subscribe (line 253): 發送了 " + i + " 可接收事件数量 = " + emitter.requested());
                    emitter.onNext(i);


                }
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(AppSchedulerProvider.io())
                .observeOn(AppSchedulerProvider.ui())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.e("Jason", ".onSubscribe (line 266): ");
                        subscription = s; // 不接收事件,由另一個來接
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e("Jason", ".onNext (line 271): " + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e("Jason", ".onError (line 277): ", t);

                    }

                    @Override
                    public void onComplete() {
                        Log.e("Jason", ".onComplete (line 282): ");
                    }
                });

    }
}
