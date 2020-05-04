package com.mxdl.okhttp3.ok_chain.base;

import com.mxdl.okhttp3.ok_chain.call.RequestCall;
import com.mxdl.okhttp3.ok_chain.utils.Exceptions;

import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

public abstract class BaseRequest {
    protected String url;
    protected Object tag;
    protected Map<String, String> params;
    protected Map<String, String> headers;
    protected int id;

    protected Request.Builder builder = new Request.Builder();

    protected BaseRequest(String url, Object tag,
                          Map<String, String> params, Map<String, String> headers, int id) {
        this.url = url;
        this.tag = tag;
        this.params = params;
        this.headers = headers;
        this.id = id;

        if (url == null) {
            Exceptions.illegalArgument("url can not be null.");
        }
        builder.url(url).tag(tag);
        if (headers != null && !headers.isEmpty()){
            Headers.Builder headerBuilder = new Headers.Builder();
            for (String key : headers.keySet()) {
                headerBuilder.add(key, headers.get(key));
            }
            builder.headers(headerBuilder.build());
        }
    }


    public RequestCall build() {
        return new RequestCall(this);
    }

    public Request generateRequest(BaseCallback baseCallback) {
        RequestBody requestBody = buildRequestBody();
        Request request = buildRequest(requestBody);
        return request;
    }

    protected abstract RequestBody buildRequestBody();

    protected abstract Request buildRequest(RequestBody requestBody);

    public int getId() {
        return id;
    }

}
