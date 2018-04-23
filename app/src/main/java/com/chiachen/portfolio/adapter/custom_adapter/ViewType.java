package com.chiachen.portfolio.adapter.custom_adapter;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


public final class ViewType {
    public static final int SHOW_ITEM = 0;
    public static final int SHOW_HEADER = 1;
    public static final int SHOW_FOOTER = 2;

    @IntDef({SHOW_ITEM, SHOW_HEADER, SHOW_FOOTER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Item {
    }
}
