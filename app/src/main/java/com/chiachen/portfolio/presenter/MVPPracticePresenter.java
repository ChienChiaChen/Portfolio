package com.chiachen.portfolio.presenter;

import android.os.AsyncTask;
import android.os.SystemClock;

import com.chiachen.portfolio.models.Counter;
import com.chiachen.portfolio.models.CounterDatabase;
import com.chiachen.portfolio.view.IMVPPracticeView;

import java.util.List;

/**
 * Created by jianjiacheng on 04/03/2018.
 */

public class MVPPracticePresenter extends BasePresenter<List<Counter>, IMVPPracticeView> {
    private boolean isLoadingData = false;

    public MVPPracticePresenter(IMVPPracticeView view) {
        super(view);
    }

    @Override
    public void syncView() {
        super.syncView();

        if (null == model && !isLoadingData) {
            getView().showLoading();
            loadData();
        }
    }

    @Override
    protected void updateView() {
        this.getView().showEmpty();
    }

    private void loadData() {
        isLoadingData = true;
        new LoadDataTask().execute();
    }

    private class LoadDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            SystemClock.sleep(3000);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            setModel(CounterDatabase.getInstance().getAllCounters());
            isLoadingData = false;
        }
    }
}
