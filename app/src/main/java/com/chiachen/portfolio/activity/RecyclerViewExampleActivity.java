package com.chiachen.portfolio.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.adapter.custom_adapter.MyAdapter;
import com.chiachen.portfolio.adapter.custom_adapter.MyDecoration;
import com.chiachen.portfolio.network.AppSchedulerProvider;
import com.chiachen.portfolio.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class RecyclerViewExampleActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mMyAdapter;
    private List<String> mList;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_example);

        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.layout_swipe_refresh);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshLayout.setRefreshing(false);
                mRefreshLayout.setEnabled(false);

                showProgressDialog();
                if (!CollectionUtils.isNullOrEmpty(mList) && null != mMyAdapter){
                    Observable.timer(5, TimeUnit.SECONDS)
                            .subscribeOn(AppSchedulerProvider.io())
                            .observeOn(AppSchedulerProvider.ui())
                            .subscribe(new Consumer<Long>() {
                                @Override
                                public void accept(Long aLong) throws Exception {
                                    mList.add(0,"It's From Upper Refresh Layout");
                                    mMyAdapter.notifyDataSetChanged();
                                    mRefreshLayout.setRefreshing(false);
                                    dismissProgressDialog();
                                }
                            });
                }
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        initFakeData();
        mMyAdapter = new MyAdapter(mList);
        mRecyclerView.setAdapter(mMyAdapter);

        // setHeaderView(mRecyclerView);
        // setFooterView(mRecyclerView);
        mRecyclerView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
    }


    private void initFakeData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add("item" + i);
        }
    }

    private void setHeaderView(RecyclerView view){
        View header = LayoutInflater.from(this).inflate(R.layout.item_header, view, false);
        mMyAdapter.setHeaderView(header);
    }

    private void setFooterView(RecyclerView view){
        View footer = LayoutInflater.from(this).inflate(R.layout.item_footer, view, false);
        mMyAdapter.setFooterView(footer);
    }

    public ProgressDialog progressDialog;

    public ProgressDialog showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.progress_button_downloading));
        progressDialog.show();
        return progressDialog;
    }

    public ProgressDialog showProgressDialog(CharSequence message) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(message);
        progressDialog.show();
        return progressDialog;
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}