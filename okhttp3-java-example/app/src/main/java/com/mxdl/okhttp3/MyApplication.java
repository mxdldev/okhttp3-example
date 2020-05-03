package com.mxdl.okhttp3;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Description: <MyApplication><br>
 * Author:      mxdl<br>
 * Date:        2020/5/2<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
