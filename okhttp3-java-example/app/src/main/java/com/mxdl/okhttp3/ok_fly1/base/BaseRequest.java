package com.mxdl.okhttp3.ok_fly1.base;


import com.mxdl.okhttp3.ok_fly1.call.RequestCall;

import java.util.HashMap;

import okhttp3.Request;

/**
 * Description: <BaseRequest><br>
 * Author:      mxdl<br>
 * Date:        2020/5/14<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public abstract class BaseRequest {
    //请求的url
    protected String url;
    //请求的Headders
    protected HashMap<String, String> headers = new HashMap<>();
    //请求的参数
    protected HashMap<String, String> parsms = new HashMap<>();

    public BaseRequest(String url, HashMap<String, String> headers, HashMap<String, String> parsms) {
        this.url = url;
        this.headers = headers;
        this.parsms = parsms;
    }

    public RequestCall buildCall(){
      return  new RequestCall(this);
    }
    public abstract Request builderRequest();
}
