package com.chiachen.portfolio.activity.page.path;

import android.content.Context;

import com.chiachen.portfolio.activity.page.RoutingPageDActivity;

/**
 * Created by jianjiacheng on 12/04/2018.
 */

public class PageD extends AbstractPath {

    public PageD(int currentPage,Context context) {
        super(currentPage, context);
    }

    @Override
    public void execute() {
        Router.goToPage(mContext, RoutingPageDActivity.class, false);
    }
}
