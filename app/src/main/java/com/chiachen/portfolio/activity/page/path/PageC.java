package com.chiachen.portfolio.activity.page.path;

import android.content.Context;

import com.chiachen.portfolio.activity.page.RoutingPageCActivity;

/**
 * Created by jianjiacheng on 12/04/2018.
 */

public class PageC extends AbstractPath {

    public PageC(int currentPage, Context context) {
        super(currentPage, context);
    }

    @Override
    public void execute() {
        Router.goToPage(mContext, RoutingPageCActivity.class, false);
    }
}
