package com.chiachen.portfolio.adapter;

import com.chiachen.portfolio.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by jianjiacheng on 05/03/2018.
 */

public abstract class MvpRecyclerListAdapter<M, P extends BasePresenter, VH extends MvpViewHolder<P>>
        extends MvpRecyclerAdapter<M, P, VH> {
    private final List<M> models;

    public MvpRecyclerListAdapter() {
        models = new ArrayList<>();
    }

    @Override
    protected M getItem(int position) {
        return models.get(position);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public void clearAndAddAll(Collection<M> data) {
        models.clear();
        presenters.clear();

        for (M item : data) {
            addInternal(item);
        }
        notifyDataSetChanged();
    }

    private void addInternal(M item) {
        models.add(item);
        Object object = getModelId(item);
        P p = createPresenter(item);
        presenters.put(object, p);
    }
}
