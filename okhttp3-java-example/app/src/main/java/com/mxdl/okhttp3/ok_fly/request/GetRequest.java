package com.mxdl.okhttp3.ok_fly.request;

import com.mxdl.okhttp3.ok_fly.base.BaseRequest;

import java.util.HashMap;
import java.util.Iterator;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Description: <GetRequest><br>
 * Author:      mxdl<br>
 * Date:        2020/5/5<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class GetRequest extends BaseRequest {
    public GetRequest(int id, String url, HashMap<String, String> heads, HashMap<String, String> params) {
        super(id, url, heads, params);
    }

    @Override
    public Request buildRequest() {
        HttpUrl.Builder httpUrl = HttpUrl.parse(url).newBuilder();
        if(params != null && !params.isEmpty()){
            Iterator<String> iterator = params.keySet().iterator();
            while (iterator.hasNext()){
                String key = iterator.next();
                httpUrl.addQueryParameter(key,params.get(key));
            }
        }
      return  builder.url(httpUrl.build()).build();
    }
}
