package com.mxdl.okhttp3.ok_fly1.request;

import com.google.gson.Gson;
import com.mxdl.okhttp3.ok_fly1.base.BaseRequest;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Description: <PostRequest><br>
 * Author:      mxdl<br>
 * Date:        2020/5/14<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class PostRequest extends BaseRequest {
    private Object body;

    public PostRequest(String url, Object body, HashMap<String, String> headers, HashMap<String, String> parsms) {
        super(url, headers, parsms);
        this.body = body;
    }

    @Override
    public Request builderRequest() {
        return new Request.Builder().url(url)
                .post(RequestBody.create(MediaType.parse("application/json;charset=utf-8"), new Gson().toJson(body)))
                .build();
    }
}
