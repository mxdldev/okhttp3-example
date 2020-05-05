package com.mxdl.okhttp3.ok_fly.call;

import com.mxdl.okhttp3.ok_fly.FlyHttpUtil;
import com.mxdl.okhttp3.ok_fly.base.BaseRequest;
import com.mxdl.okhttp3.ok_fly.callback.RequestListener;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Description: <RequestCall><br>
 * Author:      mxdl<br>
 * Date:        2020/5/5<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class RequestCall {
    private Call mCall;
    private BaseRequest mBaseRequest;

    public RequestCall(BaseRequest baseRequest) {
        mBaseRequest = baseRequest;
    }

    public <T> void execute(RequestListener<T> listener) {
        Request request = mBaseRequest.buildRequest();
        mCall = FlyHttpUtil.getInstance().getHttpClient().newCall(request);
        FlyHttpUtil.getInstance().execute(this,listener);
    }

    public Call getCall() {
        return mCall;
    }
}
