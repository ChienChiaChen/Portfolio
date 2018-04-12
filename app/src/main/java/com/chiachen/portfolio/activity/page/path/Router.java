package com.chiachen.portfolio.activity.page.path;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by jianjiacheng on 12/04/2018.
 */

public class Router {

    public static void goToPage(Context context, Class<?> cls, boolean isAllowBack) {
        goToPage(context, cls, null, isAllowBack);
    }

    private static void goToPage(Context context, Class<?> cls, Bundle extraData, boolean isAllowBack) {
        Intent i = new Intent(context, cls);

        if (!isAllowBack) {
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }

        if (null != extraData) {
            i.putExtras(extraData);
        }

        context.startActivity(i);
    }
}
