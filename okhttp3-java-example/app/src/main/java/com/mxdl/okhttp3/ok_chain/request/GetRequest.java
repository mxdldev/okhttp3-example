package com.mxdl.okhttp3.ok_chain.request;

import com.mxdl.okhttp3.ok_chain.base.BaseRequest;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

public class GetRequest extends BaseRequest {
    public GetRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers, int id) {
        super(url, tag, params, headers, id);
    }

    @Override
    protected RequestBody buildRequestBody() {
        return null;
    }

    @Override
    protected Request buildRequest(RequestBody requestBody) {
        HttpUrl.Builder httpUrl = HttpUrl.parse(url).newBuilder();
        if(params != null && !params.isEmpty()){
            Set<String> strings = params.keySet();
            Iterator<String> iterator = strings.iterator();
            while(iterator.hasNext()){
                String key = iterator.next();
                httpUrl.addQueryParameter(key,params.get(key));
            }
        }
        return builder.get().url(httpUrl.build()).build();
    }


}
