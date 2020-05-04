package com.mxdl.okhttp3.ok_chain.call;

import com.mxdl.okhttp3.ok_chain.OkHttpUtils;
import com.mxdl.okhttp3.ok_chain.base.BaseRequest;
import com.mxdl.okhttp3.ok_chain.base.BaseCallback;
import okhttp3.Call;
import okhttp3.Request;

public class RequestCall {
    private BaseRequest mBaseRequest;
    private Request request;
    private Call call;


    public RequestCall(BaseRequest request) {
        this.mBaseRequest = request;
    }

    public void execute(BaseCallback baseCallback) {
        request = generateRequest(baseCallback);
        call = OkHttpUtils.getInstance().getOkHttpClient().newCall(request);
        if (baseCallback != null) {
            baseCallback.onStart(request, getBaseRequest().getId());
        }
        OkHttpUtils.getInstance().execute(this, baseCallback);
    }

    private Request generateRequest(BaseCallback baseCallback) {
        return mBaseRequest.generateRequest(baseCallback);
    }

    public BaseRequest getBaseRequest() {
        return mBaseRequest;
    }

    public Call getCall() {
        return call;
    }

    public Request getRequest() {
        return request;
    }

    public void cancel() {
        if (call != null) {
            call.cancel();
        }
    }

}
