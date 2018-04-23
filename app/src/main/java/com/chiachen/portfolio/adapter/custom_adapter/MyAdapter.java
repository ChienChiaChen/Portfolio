package com.chiachen.portfolio.adapter.custom_adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chiachen.portfolio.R;

import java.util.List;

/**
 * Created by jianjiacheng on 22/04/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_FOOTER = 1;
    public static final int TYPE_NORMAL = 2;

    private List<String> mDatas;
    private View mHeaderView;
    private View mFooterView;

    public MyAdapter(List<String> list) {
        this.mDatas = list;
    }


    public View getHeaderView() {
        return mHeaderView;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getFooterView() {
        return mFooterView;
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount() - 1);
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null && mFooterView == null) {
            return TYPE_NORMAL;
        }
        if (position == 0) {
            return TYPE_HEADER;
        }
        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new ListHolder(mHeaderView);
        }
        if (mFooterView != null && viewType == TYPE_FOOTER) {
            return new ListHolder(mFooterView);
        }
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ListHolder(layout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_NORMAL) {
            if (holder instanceof ListHolder) {
                ((ListHolder) holder).tv.setText(mDatas.get(position - 1));
                return;
            }
            return;
        } else if (getItemViewType(position) == TYPE_HEADER) {
            return;
        } else {
            return;
        }
    }

    class ListHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public ListHolder(View itemView) {
            super(itemView);
            if (itemView == mHeaderView) {
                return;
            }
            if (itemView == mFooterView) {
                return;
            }
            tv = (TextView) itemView.findViewById(R.id.item);
        }

    }

    @Override
    public int getItemCount() {
        if (mHeaderView == null && mFooterView == null) {
            return mDatas.size();
        } else if (mHeaderView == null && mFooterView != null) {
            return mDatas.size() + 1;
        } else if (mHeaderView != null && mFooterView == null) {
            return mDatas.size() + 1;
        } else {
            return mDatas.size() + 2;
        }
    }
}
