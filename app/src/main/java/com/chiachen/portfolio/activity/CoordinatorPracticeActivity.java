package com.chiachen.portfolio.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.adapter.custom_adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class CoordinatorPracticeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<String> myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_practice);

        myList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            myList.add("item " + (i + 1));
        }
        myAdapter = new MyAdapter(myList);
        recyclerView = findViewById(R.id.rv_content);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

    }
}
