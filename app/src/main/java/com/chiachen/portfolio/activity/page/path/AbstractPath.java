package com.chiachen.portfolio.activity.page.path;

import android.content.Context;

/**
 * Created by jianjiacheng on 12/04/2018.
 */

public abstract class AbstractPath {
    public static final String PAGE = "PAGE";
    public static int GoToA = 0;
    public static int GoToB = 1;
    public static int GoToC = 2;
    public static int GoToD = 3;

    private AbstractPath mNext;
    private int mCurrentPage;
    protected Context mContext;

    public AbstractPath(int currentPage,Context context) {
        mCurrentPage = currentPage;
        mContext = context;
    }

    public void setNext(AbstractPath next) {
        mNext = next;
    }

    public void goNext(int page) {
        if (this.mCurrentPage == page) {
            execute();
            return;
        }

        if (mNext != null) {
            mNext.goNext(page);
        }
    }

    public abstract void execute();
}
