package com.chiachen.portfolio.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.adapter.custom_adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewExampleActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mMyAdapter;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_example);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        initFakeData();
        mMyAdapter = new MyAdapter(mList);
        mRecyclerView.setAdapter(mMyAdapter);

        setHeaderView(mRecyclerView);
        setFooterView(mRecyclerView);
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
}