package com.chiachen.portfolio.adapter.githubrepos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.network.response.Repo;

/**
 * Created by jianjiacheng on 15/03/2018.
 */

public class RepoViewHolder extends RecyclerView.ViewHolder {

    private TextView mRepoName;
    private TextView mRepoDetail;

    public RepoViewHolder(View itemView) {
        super(itemView);
        mRepoName = itemView.findViewById(R.id.item_repo_name);
        mRepoDetail = itemView.findViewById(R.id.item_repo_detail);
    }

    public void bindTo(Repo repo) {
        mRepoName.setText(repo.name);
        mRepoDetail.setText(String.valueOf(repo.description + "( " + repo.language + " )"));
    }
}
