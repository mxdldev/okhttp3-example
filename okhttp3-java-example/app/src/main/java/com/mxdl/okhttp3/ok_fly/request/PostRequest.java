package com.mxdl.okhttp3.ok_fly.request;

import com.google.gson.Gson;
import com.mxdl.okhttp3.ok_fly.base.BaseRequest;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Description: <PostRequest><br>
 * Author:      mxdl<br>
 * Date:        2020/5/5<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class PostRequest extends BaseRequest {
    private Object body;
    public PostRequest(int id, String url, HashMap<String, String> heads, HashMap<String, String> params,Object body) {
        super(id, url, heads, params);
        this.body = body;
    }

    @Override
    public Request buildRequest() {
        if(body != null ){
            builder.post(RequestBody.create(new Gson().toJson(body),MediaType.parse("application/json;charset=utf-8")));
        }
       return builder.build();
    }
}
