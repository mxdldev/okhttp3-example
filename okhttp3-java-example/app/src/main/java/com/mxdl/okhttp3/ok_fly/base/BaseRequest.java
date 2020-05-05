package com.mxdl.okhttp3.ok_fly.base;

import android.app.DownloadManager;
import android.text.TextUtils;

import com.mxdl.okhttp3.ok_fly.call.RequestCall;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import okhttp3.Request;

/**
 * Description: <BaseRequest><br>
 * Author:      mxdl<br>
 * Date:        2020/5/5<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public abstract class BaseRequest {
    protected int id;
    protected String url;
    protected HashMap<String, String> params = new HashMap<>();
    protected HashMap<String, String> heads = new HashMap<>();
    protected Request.Builder builder = new Request.Builder();

    public BaseRequest(int id, String url, HashMap<String, String> heads, HashMap<String, String> params) {
        this.id = id;
        this.url = url;
        this.heads = heads;
        this.params = params;
        if (TextUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url not null");
        }
        builder.url(url);
        if (heads != null && !heads.isEmpty()) {
            Set<String> keySet = heads.keySet();
            Iterator<String> iterator = keySet.iterator();
            if (iterator.hasNext()) {
                String key = iterator.next();
                builder.addHeader(key, heads.get(key));
            }
        }
    }

    public abstract Request buildRequest();

    public RequestCall buildCall() {
        return new RequestCall(this);
    }
}
