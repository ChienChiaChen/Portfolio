package com.chiachen.portfolio.presenter;

import android.os.Bundle;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by jianjiacheng on 04/03/2018.
 */

public class PresenterManager {
    private static final String KEY_PRESENTER_ID = "KEY_PRESENTER_ID";
    private static PresenterManager sManager;
    private AtomicLong currentId;
    private Cache<Long, BasePresenter<?, ?>> mPresenterCache;

    private PresenterManager(long maxSize, long expirationValue, TimeUnit expirationUnit) {
        this.currentId = new AtomicLong();
        mPresenterCache = CacheBuilder.newBuilder().expireAfterWrite(expirationValue, expirationUnit).maximumSize(maxSize).build();
    }

    public PresenterManager getInstance() {
        if (null == sManager) {
            sManager = new PresenterManager(10, 30, TimeUnit.SECONDS);
        }

        return sManager;
    }

    public void setPresenter(BasePresenter<?, ?> presenter, Bundle bundle) {
        long presenterId = currentId.incrementAndGet();
        mPresenterCache.put(presenterId, presenter);// Saving the presenter in cache.
        bundle.putLong(KEY_PRESENTER_ID, presenterId);// Saving presenter id in bundle
    }

    public <P extends BasePresenter<?, ?>> P restorePresenter(Bundle bundle) {
        Long presenterId = bundle.getLong(KEY_PRESENTER_ID);
        P presenter = (P) mPresenterCache.getIfPresent(presenterId);
        mPresenterCache.invalidate(presenterId);
        return presenter;
    }
}
