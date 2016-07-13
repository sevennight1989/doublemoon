package com.huazhu.utils;

import android.util.Log;

/**
 * Created by uiprj on 7/13/16.
 */
public class LogUtils {

    private static final String TAG = "huazhu";
    static boolean enableDebug = true;


    public static void logD(String str) {

        if (enableDebug) {
            Log.d(TAG, str);
        }

    }
}
