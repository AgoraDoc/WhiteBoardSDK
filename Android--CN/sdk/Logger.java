package com.herewhite.sdk;

import android.util.Log;

/**
 * // TODO 在文档中隐藏？
 */
public class Logger {

    public final static String LOG_TAG = "WhiteSDK";

    public static void error(String msg, Throwable throwable) {
        Log.e(LOG_TAG, msg, throwable);
    }

    public static void info(String msg) {
        Log.i(LOG_TAG, msg);
    }
}
