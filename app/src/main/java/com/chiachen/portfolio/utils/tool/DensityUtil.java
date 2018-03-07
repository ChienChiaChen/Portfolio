package com.chiachen.portfolio.utils.tool;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by jianjiacheng on 07/03/2018.
 */

public class DensityUtil {

    public static int dp2px(Context context, int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources().getDisplayMetrics());
    }
}
