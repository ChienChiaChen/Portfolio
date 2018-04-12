package com.chiachen.portfolio.activity.page.path;

import android.content.Context;

import com.chiachen.portfolio.activity.page.RoutingPageBActivity;

/**
 * Created by jianjiacheng on 12/04/2018.
 */

public class PageB extends AbstractPath {
    public PageB(int currentPage, Context context) {
        super(currentPage, context);
    }

    @Override
    public void execute() {
        Router.goToPage(mContext, RoutingPageBActivity.class, false);
    }
}
