package com.mxdl.okhttp3.ok_fly.builder;

import com.mxdl.okhttp3.ok_fly.base.BaseBuilder;
import com.mxdl.okhttp3.ok_fly.base.BaseRequest;
import com.mxdl.okhttp3.ok_fly.call.RequestCall;
import com.mxdl.okhttp3.ok_fly.request.GetRequest;

/**
 * Description: <GetBuilder><br>
 * Author:      mxdl<br>
 * Date:        2020/5/5<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class GetBuilder extends BaseBuilder<GetBuilder> {
    @Override
    public BaseRequest build() {
        return new GetRequest(id,url,heads,params);
    }

    @Override
    public RequestCall buildCall() {
        return build().buildCall();
    }
}
