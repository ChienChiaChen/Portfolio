package com.chiachen.portfolio.utils.tool;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class TypefaceManager {
    public static final String GOTHAM_RND_BOLD = "gotham_rnd_bold.otf";
    public static final String GOTHAM_RND_MEDIUM = "gotham_rnd_medium.otf";
    public static final String GOTHAM_RND_BOOK = "gotham_rnd_book.otf";

    public static Typeface getTypeface(Context context, String font) {
        if (null == context) return null;

        return Typeface.createFromAsset(context.getAssets(), "fonts/" + font);
    }

    public static void setTypeface(View view, Context context, AttributeSet attrs) {
        if (view == null || context == null || attrs == null) return;

        try {
            String packageName = "http://schemas.android.com/apk/res-auto";
            String font = attrs.getAttributeValue(packageName, "custom_font");

            if (null == font) {
                return;
            }

            if (!font.contains(".")) {
                font += findFontPosfix(context, font);
            }

            if (view instanceof TextView || view instanceof EditText || view instanceof Button) {
                ((TextView) view).setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/" + font));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String findFontPosfix(Context context, String fontFileName) {
        if (isFontExist(context, "fonts/" + fontFileName + ".ttf")) {
            return ".ttf";
        } else if (isFontExist(context, "fonts/" + fontFileName + ".otf")) {
            return ".otf";
        } else {
            return "";
        }
    }

    //TODO: Find better way to detect if font file exists
    private static boolean isFontExist(Context context, String fontFileName) {
        boolean rt = true;
        try {
            Typeface.createFromAsset(context.getAssets(), fontFileName);
        } catch (Exception e) {
            rt = false;
        }

        return rt;
    }
}
