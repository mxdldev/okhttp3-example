package com.mxdl.okhttp3.ok_chain.builder;

import com.mxdl.okhttp3.ok_chain.base.BaseRequestBuilder;
import com.mxdl.okhttp3.ok_chain.call.RequestCall;
import com.mxdl.okhttp3.ok_chain.request.GetRequest;
import java.util.LinkedHashMap;
import java.util.Map;

public class GetBuilder extends BaseRequestBuilder<GetBuilder>{
    @Override
    public RequestCall build() {
        return new GetRequest(url, tag, params, headers, id).build();
    }
    public GetBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    public GetBuilder addParams(String key, String val) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }
}
