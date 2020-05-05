package com.mxdl.okhttp3.ok_fly.base;

import com.mxdl.okhttp3.ok_fly.call.RequestCall;

import java.util.HashMap;

/**
 * Description: <BaseBuilder><br>
 * Author:      mxdl<br>
 * Date:        2020/5/5<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public abstract class BaseBuilder<T extends BaseBuilder> {
    protected int id;
    protected String url;
    protected HashMap<String,String> params = new HashMap<>();
    protected HashMap<String,String> heads = new HashMap<>();

    public T id(int id){
        this.id = id;
        return (T) this;
    }

    public T url(String url){
        this.url = url;
        return (T) this;
    }

    public T addParam(String key,String value){
        params.put(key,value);
        return (T) this;
    }
    public T addHead(String key,String value){
        heads.put(key,value);
        return (T) this;
    }

    public abstract BaseRequest build();
    public abstract RequestCall buildCall();
}
