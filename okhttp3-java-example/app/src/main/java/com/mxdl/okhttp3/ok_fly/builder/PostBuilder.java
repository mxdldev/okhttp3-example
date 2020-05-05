package com.mxdl.okhttp3.ok_fly.builder;

import com.mxdl.okhttp3.ok_fly.base.BaseBuilder;
import com.mxdl.okhttp3.ok_fly.base.BaseRequest;
import com.mxdl.okhttp3.ok_fly.call.RequestCall;
import com.mxdl.okhttp3.ok_fly.request.GetRequest;
import com.mxdl.okhttp3.ok_fly.request.PostRequest;

import okhttp3.MediaType;

/**
 * Description: <PostBuilder><br>
 * Author:      mxdl<br>
 * Date:        2020/5/5<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class PostBuilder extends BaseBuilder<PostBuilder> {
    private Object body;
    public PostBuilder body(Object body){
        this.body = body;
        return  this;
    }
    @Override
    public BaseRequest build() {
        return new PostRequest(id,url,heads,params,body);
    }

    @Override
    public RequestCall buildCall() {
        return build().buildCall();
    }
}
