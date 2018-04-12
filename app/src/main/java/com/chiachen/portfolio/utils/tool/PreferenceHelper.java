package com.chiachen.portfolio.utils.tool;


import android.content.Context;
import android.content.SharedPreferences;

public abstract class PreferenceHelper {
    public static final String TAG_NAME = "ANDROID_SETTING_CONFIG";

    public static boolean onGetPrefBoolSetting(String Tag, Context activityContext) {
        return onGetPrefBoolSetting(Tag, false, activityContext);
    }

    public static boolean onGetPrefBoolSetting(String Tag, boolean defaultValue, Context activityContext) {
        Boolean value;
        if (Tag != null && activityContext != null) {
            SharedPreferences settings = activityContext.getSharedPreferences(TAG_NAME, 0);
            value = settings.getBoolean(Tag, defaultValue);
        } else {
            throw new NullPointerException("Parameter can not be null.");
        }
        return value;
    }

    public static void onSetPrefBoolSetting(String Tag, Boolean Value, Context activityContext) {
        if (Tag != null && Value != null && activityContext != null) {
            SharedPreferences settings = activityContext.getSharedPreferences(TAG_NAME, 0);
            settings.edit().putBoolean(Tag, Value).apply();
        }
    }

    public static void onSetPrefLongSetting(String tag, long value, Context activityContext) {
        if (tag != null && activityContext != null) {
            SharedPreferences settings = activityContext.getSharedPreferences(TAG_NAME, 0);
            settings.edit().putLong(tag, value).apply();
        }
    }

    public static long onGetPrefLongSetting(String tag, Context activityContext) {
        long value;
        if (tag != null && activityContext != null) {
            SharedPreferences settings = activityContext.getSharedPreferences(TAG_NAME, 0);
            value = settings.getLong(tag, 0);
        } else {
            throw new NullPointerException("Parameter can not be null.");
        }
        return value;
    }

    public static void onSetPrefStringSetting(String tag, String value, Context activityContext) {
        if (tag != null && value != null && activityContext != null) {
            SharedPreferences settings = activityContext.getSharedPreferences(TAG_NAME, 0);
            settings.edit().putString(tag, value).apply();
        }
    }

    public static String onGetPrefStringSetting(String tag, Context activityContext) {
        return onGetPrefStringSetting(tag, "", activityContext);
    }

    public static String onGetPrefStringSetting(String tag, String defaultValue, Context activityContext) {
        String value;
        if (tag != null && defaultValue != null && activityContext != null) {
            SharedPreferences settings = activityContext.getSharedPreferences(TAG_NAME, 0);
            value = settings.getString(tag, defaultValue);
        } else {
            throw new NullPointerException("Parameter can not be null.");
        }
        return value;
    }

    public static void onSetPrefLong(String sharedPreferencesName, String tag, long value, Context activityContext) {
        if (sharedPreferencesName != null && tag != null && activityContext != null) {
            SharedPreferences settings = activityContext.getSharedPreferences(sharedPreferencesName, 0);
            settings.edit().putLong(tag, value).apply();
        }
    }

    public static long onGetPrefLong(String sharedPreferencesName, String tag, Context activityContext) {
        long value;
        if (sharedPreferencesName != null && tag != null && activityContext != null) {
            SharedPreferences settings = activityContext.getSharedPreferences(sharedPreferencesName, 0);
            value = settings.getLong(tag, 0);
        } else {
            throw new NullPointerException("Parameter can not be null.");
        }
        return value;
    }


    public static void onSetPrefString(String sharedPreferencesName, String key, String value, Context activityContext) {
        if (sharedPreferencesName != null && key != null && value != null && activityContext != null) {
            SharedPreferences settings = activityContext.getSharedPreferences(sharedPreferencesName, 0);
            settings.edit().putString(key, value).apply();
        }
    }

    public static String onGetPrefString(String sharedPreferencesName, String key, String defaultValue, Context activityContext) {
        String value;

        if (sharedPreferencesName != null && key != null && defaultValue != null && activityContext != null) {
            SharedPreferences settings = activityContext.getSharedPreferences(sharedPreferencesName, 0);
            value = settings.getString(key, defaultValue);
        } else {
            throw new NullPointerException("Parameter can not be null.");
        }
        return value;
    }

    public static int onGetPrefIntSetting(String sharedPreferencesName, String key, int defaultValue, Context activityContext) {
        int value;
        if (sharedPreferencesName != null && activityContext != null) {
            SharedPreferences settings = activityContext.getSharedPreferences(TAG_NAME, 0);
            value = settings.getInt(key, defaultValue);
        } else {
            throw new NullPointerException("Parameter can not be null.");
        }
        return value;
    }

    public static void onSetPrefIntSetting(String sharedPreferencesName, String key, int value, Context activityContext) {
        if (sharedPreferencesName != null && key != null && activityContext != null) {
            SharedPreferences settings = activityContext.getSharedPreferences(sharedPreferencesName, 0);
            settings.edit().putInt(key, value).apply();
        }
    }

    public static int onGetPrefInt(String sharedPreferencesName, String tag, Context activityContext) {
        int value;
        if (sharedPreferencesName != null && tag != null && activityContext != null) {
            SharedPreferences settings = activityContext.getSharedPreferences(sharedPreferencesName, 0);
            value = settings.getInt(tag, 0);
        } else {
            throw new NullPointerException("Parameter can not be null.");
        }
        return value;
    }

    public static void removePref(String tag, Context activityContext) {
        removePref(TAG_NAME, tag, activityContext);
    }

    public static void removePref(String sharedPreferencesName, String tag, Context activityContext) {
        SharedPreferences settings = activityContext.getSharedPreferences(sharedPreferencesName, 0);
        settings.edit().remove(tag).apply();
    }

    public static void clearPref(String sharedPreferencesName, Context activityContext) {
        SharedPreferences settings = activityContext.getSharedPreferences(sharedPreferencesName, 0);
        settings.edit().clear().apply();
    }
}
