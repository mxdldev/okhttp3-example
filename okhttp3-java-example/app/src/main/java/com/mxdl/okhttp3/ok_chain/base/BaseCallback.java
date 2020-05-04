package com.mxdl.okhttp3.ok_chain.base;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public abstract class BaseCallback<T> {

    public void onStart(Request request, int id) {
    }

    public abstract void onFail(Call call, Exception e, int id);

    public abstract void onSucc(T response, int id);

    public void onComplete(int id) {
    }

    public void inProgress(float progress, long total, int id) {
    }
    public abstract T parseNetworkResponse(Response response, int id) throws Exception;

}