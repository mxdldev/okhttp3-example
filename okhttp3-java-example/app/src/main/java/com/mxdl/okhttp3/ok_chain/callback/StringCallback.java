package com.mxdl.okhttp3.ok_chain.callback;

import com.mxdl.okhttp3.ok_chain.base.BaseCallback;

import java.io.IOException;

import okhttp3.Response;

public abstract class StringCallback extends BaseCallback<String>
{
    @Override
    public String parseNetworkResponse(Response response, int id) throws IOException
    {
        return response.body().string();
    }
}
