package com.mxdl.okhttp3.ok_fly1.call;

import com.mxdl.okhttp3.ok_fly1.FlyHttpUtils;
import com.mxdl.okhttp3.ok_fly1.base.BaseRequest;
import com.mxdl.okhttp3.ok_fly1.response.OnResponse;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description: <RequestCall><br>
 * Author:      mxdl<br>
 * Date:        2020/5/14<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class RequestCall {
    private BaseRequest request;
    private Call call;

    public RequestCall(BaseRequest request) {
        this.request = request;
    }

    public void execute(OnResponse response){
        Request request = this.request.builderRequest();
        call = FlyHttpUtils.getInstance().getOkHttpClient().newCall(request);
        FlyHttpUtils.getInstance().execute(this,response);
    }

    public Call getCall() {
        return call;
    }
}
