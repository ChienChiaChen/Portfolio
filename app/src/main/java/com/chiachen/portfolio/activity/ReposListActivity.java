package com.chiachen.portfolio.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.adapter.githubrepos.ListAdapter;
import com.chiachen.portfolio.global.BaseApplication;
import com.chiachen.portfolio.network.AppSchedulerProvider;
import com.chiachen.portfolio.network.Service.GitHubService;
import com.chiachen.portfolio.network.response.Repo;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public class ReposListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Inject
    GitHubService mGitHubService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos_list);

        BaseApplication.component().inject(this);

        mRecyclerView = findViewById(R.id.repos_list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(manager);
        ListAdapter listAdapter = new ListAdapter();
        mRecyclerView.setAdapter(listAdapter);


        loadData(listAdapter);
    }

    private void loadData(final ListAdapter listAdapter) {
        mGitHubService
                .getRepoData("JasonChienPrenetics")
                .subscribeOn(AppSchedulerProvider.io())
                .observeOn(AppSchedulerProvider.ui())
                .subscribe(new Consumer<ArrayList<Repo>>() {
                    @Override
                    public void accept(ArrayList<Repo> repos) throws Exception {
                        listAdapter.setRepos(repos);
                    }
                });
    }
}
