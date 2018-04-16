package com.chiachen.portfolio.utils.tool.download;

import android.os.AsyncTask;
import android.os.SystemClock;

import com.chiachen.portfolio.activity.ContinuousDownloadActivity;

import java.lang.ref.WeakReference;

public class CustomAsyncTask extends AsyncTask<Void, Integer, Void> {
    private WeakReference<ContinuousDownloadActivity> mActivity; // Avoid memory leak

    private boolean mCompleted = false;

    public void setActivity(ContinuousDownloadActivity activity) {
        mActivity = new WeakReference<>(activity);
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    @Override
    protected Void doInBackground(Void... params) {
        for (int i = 1; i < ContinuousDownloadActivity.MAX_PROGRESS + 1; i++) {
            SystemClock.sleep(ContinuousDownloadActivity.EMIT_DELAY_MS);
            publishProgress(i); // Update progress
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        mActivity.get().setProgressValue(progress[0]); // update the value of progress
        mActivity.get().setProgressPercentText(progress[0]); // Update percent text.
    }

    @Override
    protected void onPreExecute() {
        mActivity.get().setProgressText("Start async task...");
        mCompleted = false;
    }

    @Override
    protected void onPostExecute(Void result) {
        mCompleted = true;
        mActivity.get().setBusy(false);
        mActivity.get().setProgressValue(0);
    }
}
