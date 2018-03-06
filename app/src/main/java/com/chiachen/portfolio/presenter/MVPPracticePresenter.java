package com.chiachen.portfolio.presenter;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.annotation.NonNull;

import com.chiachen.portfolio.models.Counter;
import com.chiachen.portfolio.models.CounterDatabase;
import com.chiachen.portfolio.view.IMVPPracticeView;

import java.util.List;

/**
 * Created by jianjiacheng on 04/03/2018.
 */

public class MVPPracticePresenter extends BasePresenter<List<Counter>, IMVPPracticeView> {
    private boolean isLoadingData = false;

    @Override
    public void bindView(@NonNull IMVPPracticeView view) {
        super.bindView(view);

        if (null == model && !isLoadingData) {
            getView().showLoading();
            loadData();
        }
    }

    @Override
    protected void updateView() {

        // Logic is in the presenter.
        if (0 == model.size()) {
            this.getView().showEmpty();
        } else {
            this.getView().showCounters(model);
        }
    }

    private void loadData() {
        isLoadingData = true;
        new LoadDataTask().execute();
    }

    public void onAddCounterClicked() {
        Counter counter = new Counter();
        counter.setName("New one");

        // update view immediately
        model.add(counter);
        CounterDatabase.getInstance().saveCounter(counter);
        updateView();
    }

    private class LoadDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            SystemClock.sleep(500);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            setModel(CounterDatabase.getInstance().getAllCounters());
            isLoadingData = false;
        }
    }
}
