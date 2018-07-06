package com.chiachen.portfolio.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jianjiacheng on 2018/7/6.
 */

public class TimeUtil {
    public static String getNowStrTime(){
        long time = System.currentTimeMillis();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(time));
    }
}
