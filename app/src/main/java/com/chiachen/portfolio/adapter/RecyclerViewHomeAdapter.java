package com.chiachen.portfolio.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chiachen.portfolio.R;

import java.util.Map;

/**
 * Created by jianjiacheng on 27/04/2018.
 */

public class RecyclerViewHomeAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context mContext;
    private Map<Integer, String> mMap;
    private OnItemClickListener mOnItemClickListener;

    public RecyclerViewHomeAdapter(Context context, Map<Integer, String>  map) {
        mContext = context;
        mMap = map;
    }

    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public RecyclerViewHomeAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        return this;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_grid, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        if (null == mOnItemClickListener) {
            return;
        }

        ((TextView) holder.itemView.findViewById(R.id.item_grid_name)).setText(mMap.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getLayoutPosition();
                Log.e("JASON_CHIEN", "\nOnClick Position: " + pos);
                mOnItemClickListener.ItemClickListener(holder.itemView, pos);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int pos = holder.getLayoutPosition();
                Log.e("JASON_CHIEN", "\nOnLongClick Position: " + pos);
                mOnItemClickListener.ItemLongClickListener(holder.itemView, pos);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMap.size();
    }
}
