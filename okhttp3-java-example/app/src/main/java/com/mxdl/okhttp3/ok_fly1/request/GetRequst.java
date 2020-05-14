package com.mxdl.okhttp3.ok_fly1.request;

import com.mxdl.okhttp3.ok_fly1.base.BaseRequest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Description: <GetRequst><br>
 * Author:      mxdl<br>
 * Date:        2020/5/14<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class GetRequst extends BaseRequest {
    public GetRequst(String url, HashMap<String, String> headers, HashMap<String, String> parsms) {
        super(url, headers, parsms);
    }

    @Override
    public Request builderRequest() {
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        if(!parsms.isEmpty()){
            Iterator<String> iterator = parsms.keySet().iterator();
            while (iterator.hasNext()){
                String key = iterator.next();
                builder.addQueryParameter(key,parsms.get(key));
            }
        }
        return new Request.Builder().url(builder.build()).build();
    }
}
