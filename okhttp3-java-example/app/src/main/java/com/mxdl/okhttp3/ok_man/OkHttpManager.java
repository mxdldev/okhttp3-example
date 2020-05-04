package com.mxdl.okhttp3.ok_man;

import com.google.gson.Gson;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.mxdl.okhttp3.ok_man.response.MyCallBack;
import com.mxdl.okhttp3.ok_man.response.OnResponse;

import java.util.HashMap;
import java.util.Set;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.internal.platform.Platform;

/**
 * Description: <OkHttpManager><br>
 * Author:      mxdl<br>
 * Date:        2020/5/2<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class OkHttpManager {
    private OkHttpClient mHttpClient;

    private OkHttpManager() {
        mHttpClient = new OkHttpClient.Builder().addInterceptor(
                new LoggingInterceptor
                        .Builder()
                        .setLevel(Level.BODY)
                        .log(Platform.INFO)
                        .request("request")
                        .response("reponse")
                        .build())
                .build();
    }

    public static OkHttpManager getInstance() {
        return OkHttpManagerHolder.mHttpManager;
    }

    public <T> void post(String url, Object body, OnResponse<T> response) {
        String jsonBody = new Gson().toJson(body);
        MyCallBack callback = new MyCallBack(response);
        callback.onStart();
        mHttpClient.newCall(
                new Request
                        .Builder()
                        .url(url)
                        .post(RequestBody.create(jsonBody, MediaType.parse("application/json;charset=utf-8")))
                        .build())
                .enqueue(callback);

    }

    public <T> void get(String url, HashMap<String, String> map, OnResponse<T> response) {
        HttpUrl.Builder httpUrlBuilder = HttpUrl.parse(url).newBuilder();
        if (map != null && map.size() > 0) {
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                httpUrlBuilder.addQueryParameter(key, map.get(key));
            }
        }
        Request request = new Request.Builder().url(httpUrlBuilder.build()).build();
        MyCallBack callBack = new MyCallBack(response);
        mHttpClient.newCall(request).enqueue(callBack);
    }

    static class OkHttpManagerHolder {
        static OkHttpManager mHttpManager = new OkHttpManager();
    }


}
