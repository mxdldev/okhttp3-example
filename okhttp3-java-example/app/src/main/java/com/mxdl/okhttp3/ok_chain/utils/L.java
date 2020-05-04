package com.mxdl.okhttp3.ok_chain.utils;

import android.util.Log;

public class L {
    private static boolean debug = true;

    public static void e(String msg) {
        if (debug) {
            Log.e("OkHttp", msg);
        }
    }
}

