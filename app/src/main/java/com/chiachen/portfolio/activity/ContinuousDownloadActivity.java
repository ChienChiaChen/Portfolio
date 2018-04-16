package com.chiachen.portfolio.activity;

import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.utils.tool.download.CustomAsyncTask;
import com.chiachen.portfolio.utils.tool.download.CustomIterator;
import com.chiachen.portfolio.utils.tool.download.CustomService;
import com.chiachen.portfolio.utils.tool.download.RetainedFragment;
import com.squareup.leakcanary.LeakCanary;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

public class ContinuousDownloadActivity extends AppCompatActivity {

    private static final String TAG = "Jason: " + ContinuousDownloadActivity.class.getSimpleName();

    // Spinner
    private static final int ASYNC_TASK = 0;
    private static final int INTENT_SERVICE = 1;
    private static final int TIME_INTERVAL = 2;
    private static final int DELAY_EMIT = 3;
    private static final int CUSTOM_ITERATOR = 4;

    private static final String RETAINED_FRAGMENT = "retained_fragment"; //The tag of fragment
    public final static int MAX_PROGRESS = 10;
    public final static int EMIT_DELAY_MS = 1000;
    public static final String UPDATE_PROGRESS_FILTER = "update_progress_filter";


    private RetainedFragment mRetainedFragment; // For restore state
    private Spinner mSModesSpinner;
    private Switch mSTrackLeaks;
    private TextView mTvProgressText;
    private ProgressBar mPbProgressBar;
    private Button mBStartButton;

    private CustomAsyncTask mCustomAsyncTask;
    private LocalBroadcastManager mLbm;
    private Observable<Long> mObservable;
    private Subscriber<Long> mSubscriber;
    private PublishSubject<Long> mSubject;

    private int mMode = ASYNC_TASK;

    // Service
    private BroadcastReceiver mUpdateProgressReceiver = new BroadcastReceiver() {
        @Override public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra(CustomService.KEY_EXTRA_PROGRESS)) {
                int progress = intent.getIntExtra(CustomService.KEY_EXTRA_PROGRESS, 0);
                mPbProgressBar.setProgress(progress);
                setProgressPercentText(progress);
            }

            if (intent.hasExtra(CustomService.KEY_EXTRA_BUSY)) {
                setBusy(intent.getBooleanExtra(CustomService.KEY_EXTRA_BUSY, false));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continuous_download);
        initUIComponent();
        initLocalBroadcastManager();
        initRetainedFragment();
    }

    private void initLocalBroadcastManager() {
        mLbm = LocalBroadcastManager.getInstance(getApplicationContext());
        mLbm.registerReceiver(mUpdateProgressReceiver, new IntentFilter(UPDATE_PROGRESS_FILTER));
    }

    private void initUIComponent() {
        mSModesSpinner = findViewById(R.id.main_s_modes);
        mSTrackLeaks = (Switch) findViewById(R.id.main_s_track_leaks);
        mTvProgressText = findViewById(R.id.main_tv_progress_text);
        mPbProgressBar = findViewById(R.id.main_pb_progress_bar);
        mBStartButton = findViewById(R.id.main_b_start_button);
        mPbProgressBar.setMax(MAX_PROGRESS);
        mSModesSpinner.setEnabled(mBStartButton.isEnabled());
        mBStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProgress(v);
            }
        });
        mSModesSpinner.post(new Runnable() {
            @Override
            public void run() {
                mSModesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        mMode = position;
                        mRetainedFragment.setMode(mMode);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }
        });
    }

    private void initRetainedFragment() {
        FragmentManager fm = getFragmentManager();
        mRetainedFragment = (RetainedFragment) fm.findFragmentByTag(RETAINED_FRAGMENT);

        if (mRetainedFragment == null) {
            mRetainedFragment = new RetainedFragment();
            fm.beginTransaction().add(mRetainedFragment, RETAINED_FRAGMENT).commit();
        }
    }

    /**
     * 在onResume中设置setActivity, 因为会执行onRestoreInstanceState方法,
     * 会恢复旋转屏幕之前保存的数据, mPbProgressBar的值, 再设置初始值.
     * 如果移到onCreate时设置, 则会导致Progress值为0, 因为Activity并没有开始恢复数据.
     * 生命周期: onCreate -> onRestoreInstanceState -> onResume.
     */
    @Override protected void onResume() {
        super.onResume();


        if (mSTrackLeaks.isChecked()) {
            LeakCanary.install(getApplication());
        }

        mMode = mRetainedFragment.getMode();
        mCustomAsyncTask = mRetainedFragment.getCustomAsyncTask();

        mObservable = mRetainedFragment.getObservable();
        mSubject = mRetainedFragment.getSubject();
        mSubscriber = createSubscriber();

        switch (mMode) {
            case ASYNC_TASK:
                if (mCustomAsyncTask != null) {
                    if (!mCustomAsyncTask.isCompleted()) {
                        mCustomAsyncTask.setActivity(this);
                    } else {
                        mRetainedFragment.setCustomAsyncTask(null);
                    }
                }
                break;
            case TIME_INTERVAL:
                if (mObservable != null) {
                    mObservable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .take(MAX_PROGRESS)
                            .map(new Func1<Long, Long>() {
                                @Override
                                public Long call(Long aLong) {
                                    return ++aLong;
                                }
                            })
                            .subscribe(mSubscriber);
                }
                break;
            case DELAY_EMIT:
                if (mObservable != null) {
                    mObservable.subscribeOn(Schedulers.io())
                            .delay(1, TimeUnit.SECONDS)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(mSubscriber);
                }
                break;
            case CUSTOM_ITERATOR:
                if (mSubject != null) {
                    mSubject.subscribe(mSubscriber);
                }
            default:
                break;
        }

        setBusy(mRetainedFragment.isBusy());
    }

    @Override protected void onPause() {
        super.onPause();
        if (mSubscriber != null) {
            mSubscriber.unsubscribe();
        }
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        mLbm.unregisterReceiver(mUpdateProgressReceiver);
    }

    private void startProgress(View view) {
        mMode = mSModesSpinner.getSelectedItemPosition();
        mRetainedFragment.setMode(mMode);
        setBusy(true); // 设置繁忙
        switch (mMode) {
            case ASYNC_TASK:
                handleAsyncClick();
                break;
            case INTENT_SERVICE:
                handleIntentServiceClick();
                break;
            case TIME_INTERVAL:
                handleTimeIntervalClick();
                break;
            case DELAY_EMIT:
                handleDelayEmitClick();
                break;
            case CUSTOM_ITERATOR:
                handleCustomIteratorClick();
                break;
            default:
                break;
        }
    }

    private void handleAsyncClick() {
        mCustomAsyncTask = new CustomAsyncTask();
        mCustomAsyncTask.setActivity(this);
        mRetainedFragment.setCustomAsyncTask(mCustomAsyncTask);
        mCustomAsyncTask.execute();
    }

    private void handleIntentServiceClick() {
        mTvProgressText.setText("Start service...");
        Intent intent = new Intent(this, CustomService.class);
        startService(intent);
    }

    private void handleTimeIntervalClick() {
        mTvProgressText.setText("start time interval...");
        mSubscriber = createSubscriber();
        mObservable = Observable.interval(1, TimeUnit.SECONDS);

        mObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .take(MAX_PROGRESS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return ++aLong;
                    }
                })
                .subscribe(mSubscriber);

        mRetainedFragment.setObservable(mObservable);
    }

    private void handleDelayEmitClick() {
        mTvProgressText.setText("start delay emit...");

        mSubscriber = createSubscriber();
        mObservable = createObservable();

        mObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);

        mRetainedFragment.setObservable(mObservable);
    }

    private void handleCustomIteratorClick() {
        mTvProgressText.setText("start custom iterator...");

        mObservable = Observable.from(new CustomIterator());
        mSubscriber = createSubscriber();
        mSubject = PublishSubject.create();

        mObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubject);

        mSubject.subscribe(mSubscriber);

        mRetainedFragment.setObservable(mObservable);
        mRetainedFragment.setSubject(mSubject);
    }

    public void setProgressText(String string) {
        mTvProgressText.setText(string);
    }

    public void setProgressPercentText(int value) {
        mTvProgressText.setText(String.valueOf("Progress " + value * 100 / MAX_PROGRESS + "%"));
    }

    public void setProgressValue(int value) {
        mPbProgressBar.setProgress(value);
    }

    private Subscriber<Long> createSubscriber() {
        return new Subscriber<Long>() {
            @Override public void onCompleted() {
                setBusy(false);
                mPbProgressBar.setProgress(0);
                mRetainedFragment.setObservable(null);
            }

            @Override public void onError(Throwable e) {
                setBusy(false);
                mTvProgressText.setText(String.valueOf("Error!"));
                mObservable = null;

                mRetainedFragment.setObservable(null);
            }

            @Override public void onNext(Long aLong) {
                setProgressPercentText(aLong.intValue());
                mPbProgressBar.setProgress(aLong.intValue());
            }
        };
    }

    private Observable<Long> createObservable() {
        return Observable.create(new Observable.OnSubscribe<Long>() {
            @Override public void call(Subscriber<? super Long> subscriber) {
                for (long i = 1; i < MAX_PROGRESS + 1; i++) {
                    SystemClock.sleep(EMIT_DELAY_MS);
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        });
    }

    public void setBusy(boolean busy) {
        Log.e(TAG, "progress: " + mPbProgressBar.getProgress());
        if (mPbProgressBar.getProgress() > 0 && mPbProgressBar.getProgress() != mPbProgressBar.getMax()) {
            setProgressPercentText(mPbProgressBar.getProgress());
        } else {
            Log.e(TAG, busy ? "Busy" : "Idle");
            mTvProgressText.setText(busy ? "Busy" : "Idle");
        }

        mBStartButton.setText(busy ? "Busy" : "Idle");

        mBStartButton.setEnabled(!busy);
        mSModesSpinner.setEnabled(!busy);
        mRetainedFragment.setBusy(busy);
    }
}
