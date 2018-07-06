package com.chiachen.portfolio.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatUtils {

    public static String formatWeight(float n) {
        NumberFormat format = NumberFormat.getInstance(Locale.ENGLISH);
        format.setMaximumFractionDigits(1);
        return format.format(n);
    }

}
