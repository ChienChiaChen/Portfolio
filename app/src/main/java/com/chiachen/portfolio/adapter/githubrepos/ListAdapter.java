package com.chiachen.portfolio.adapter.githubrepos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.network.response.Repo;

import java.util.ArrayList;

/**
 * Created by jianjiacheng on 15/03/2018.
 */

public class ListAdapter extends RecyclerView.Adapter<RepoViewHolder> {

    private ArrayList<Repo> mRepos;
    public ListAdapter() {
        mRepos = new ArrayList<>();
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repo, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        holder.bindTo(mRepos.get(position));
    }

    @Override
    public int getItemCount() {
        return mRepos.size();
    }

    public void setRepos(ArrayList<Repo> repos) {
        mRepos = repos;
        // notifyItemInserted(mRepos.size() - 1);
        notifyDataSetChanged();
    }
}
