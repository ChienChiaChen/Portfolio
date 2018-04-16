package com.chiachen.portfolio.utils.tool.download;

import android.os.SystemClock;

import com.chiachen.portfolio.activity.ContinuousDownloadActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomIterator implements Iterable<Long> {

    private List<Long> mNumberList = new ArrayList<>();

    public CustomIterator() {
        for (long i = 0; i < ContinuousDownloadActivity.MAX_PROGRESS; i++) {
            mNumberList.add(i + 1);
        }
    }

    @Override
    public Iterator<Long> iterator() {
        return new Iterator<Long>() {
            private int mCurrentIndex = 0;

            @Override
            public boolean hasNext() {
                return mCurrentIndex < mNumberList.size() && mNumberList.get(mCurrentIndex) != null;
            }

            @Override
            public Long next() {
                SystemClock.sleep(ContinuousDownloadActivity.EMIT_DELAY_MS);
                return mNumberList.get(mCurrentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
