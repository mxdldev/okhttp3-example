package com.mxdl.okhttp3.ok_fly1.builder;

import com.mxdl.okhttp3.ok_fly1.base.BaseBuidler;
import com.mxdl.okhttp3.ok_fly1.base.BaseRequest;
import com.mxdl.okhttp3.ok_fly1.request.PostRequest;

import java.util.Observable;

/**
 * Description: <PostBuilder><br>
 * Author:      mxdl<br>
 * Date:        2020/5/14<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class PostBuilder extends BaseBuidler<PostBuilder> {
    private Object body;
    @Override
    public BaseRequest builder() {
        return new PostRequest(url,body,headers,parsms);
    }
    public BaseBuidler<PostBuilder> body(Object body){
        this.body = body;
        return this;
    }
}
