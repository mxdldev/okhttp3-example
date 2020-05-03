package com.mxdl.okhttp3.http;

import com.mxdl.okhttp3.bean.ResDTO;

/**
 * Description: <OnResponse><br>
 * Author:      mxdl<br>
 * Date:        2020/5/2<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public abstract class OnResponse<T> {
    public abstract void onStart();

    public abstract void onSucc(ResDTO<T> resDTO);

    public abstract void onFail(Exception e);

    public abstract void onComplete();
}
