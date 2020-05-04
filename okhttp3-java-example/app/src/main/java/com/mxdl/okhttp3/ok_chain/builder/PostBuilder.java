package com.mxdl.okhttp3.ok_chain.builder;

import com.mxdl.okhttp3.ok_chain.base.BaseRequestBuilder;
import com.mxdl.okhttp3.ok_chain.call.RequestCall;
import com.mxdl.okhttp3.ok_chain.request.PostRequest;
import okhttp3.MediaType;

public class PostBuilder extends BaseRequestBuilder<PostBuilder> {
    private Object content;
    private MediaType mediaType;


    public PostBuilder content(Object content) {
        this.content = content;
        return this;
    }

    public PostBuilder mediaType(MediaType mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    @Override
    public RequestCall build() {
        return new PostRequest(url, tag, params, headers, content, mediaType, id).build();
    }


}
