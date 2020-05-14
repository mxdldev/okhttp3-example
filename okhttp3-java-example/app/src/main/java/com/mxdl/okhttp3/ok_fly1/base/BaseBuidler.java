package com.mxdl.okhttp3.ok_fly1.base;

import java.util.HashMap;

/**
 * Description: <BaseBuidler><br>
 * Author:      mxdl<br>
 * Date:        2020/5/14<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public abstract class BaseBuidler<T> {
    //请求的url
    protected String url;
    //请求的Headders
    protected HashMap<String, String> headers = new HashMap<>();
    //请求的参数
    protected HashMap<String, String> parsms = new HashMap<>();

    //构建url
    public T url(String url) {
        this.url = url;
        return (T) this;
    }

    //构建一个header参数
    public T addHeader(String key, String value) {
        headers.put(key, value);
        return (T) this;
    }

    //构建一个请求参数
    public T addParams(String key, String value) {
        parsms.put(key, value);
        return (T) this;
    }

    //最终要构建的对象
    public abstract BaseRequest builder();
}