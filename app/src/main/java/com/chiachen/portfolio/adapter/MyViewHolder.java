package com.chiachen.portfolio.adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chiachen.portfolio.R;

import java.util.Random;

/**
 * Created by jianjiacheng on 27/04/2018.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    public MyViewHolder(View itemView) {
        super(itemView);
        Random rnd = new Random();
        int red = rnd.nextInt(256), green = rnd.nextInt(256), blue = rnd.nextInt(256);
        int color = Color.argb(255, red, green, blue);
        int colorAlpha = Color.argb(120, red, green, blue);
        (itemView.findViewById(R.id.item_grid_name)).setBackgroundTintList(new ColorStateList(
                new int[][]{new int[]{android.R.attr.state_pressed}, new int[]{-android.R.attr.state_pressed}}, new int[]{colorAlpha, color,}));
    }
}
