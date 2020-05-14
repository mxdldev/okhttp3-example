package com.mxdl.okhttp3.ok_fly1.builder;

import com.mxdl.okhttp3.ok_fly1.base.BaseBuidler;
import com.mxdl.okhttp3.ok_fly1.base.BaseRequest;
import com.mxdl.okhttp3.ok_fly1.request.GetRequst;

/**
 * Description: <GetBuilder><br>
 * Author:      mxdl<br>
 * Date:        2020/5/14<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class GetBuilder extends BaseBuidler<GetBuilder> {

    @Override
    public BaseRequest builder() {
        return new GetRequst(url,headers,parsms);
    }
}
