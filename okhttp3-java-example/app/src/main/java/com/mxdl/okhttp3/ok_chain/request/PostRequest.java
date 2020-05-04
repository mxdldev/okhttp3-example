package com.mxdl.okhttp3.ok_chain.request;

import com.google.gson.Gson;
import com.mxdl.okhttp3.ok_chain.base.BaseRequest;
import com.mxdl.okhttp3.ok_chain.utils.Exceptions;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class PostRequest extends BaseRequest {
    private static MediaType MEDIA_TYPE_PLAIN = MediaType.parse("text/plain;charset=utf-8");

    private Object content;
    private MediaType mediaType;


    public PostRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers, Object content, MediaType mediaType, int id) {
        super(url, tag, params, headers, id);
        this.content = content;
        this.mediaType = mediaType;

        if (this.content == null) {
            Exceptions.illegalArgument("the content can not be null !");
        }
        if (this.mediaType == null) {
            this.mediaType = MEDIA_TYPE_PLAIN;
        }

    }

    @Override
    protected RequestBody buildRequestBody() {
        return RequestBody.create(mediaType, new Gson().toJson(content));
    }

    @Override
    protected Request buildRequest(RequestBody requestBody) {
        return builder.post(requestBody).build();
    }


}
