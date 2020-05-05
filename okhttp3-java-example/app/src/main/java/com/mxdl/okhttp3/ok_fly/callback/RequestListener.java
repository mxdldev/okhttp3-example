package com.mxdl.okhttp3.ok_fly.callback;

/**
 * Description: <RequestListener><br>
 * Author:      mxdl<br>
 * Date:        2020/5/5<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public abstract class RequestListener<T> {
    public abstract void onStart();
    public abstract void onSucc(T t);
    public abstract void onFail(Exception e);
    public abstract void onComplete();
}
