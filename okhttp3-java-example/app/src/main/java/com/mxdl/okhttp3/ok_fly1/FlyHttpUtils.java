package com.mxdl.okhttp3.ok_fly1;

import android.os.Handler;
import android.os.Looper;

import com.facebook.stetho.common.ExceptionUtil;
import com.google.gson.Gson;
import com.mxdl.okhttp3.ok_fly1.builder.GetBuilder;
import com.mxdl.okhttp3.ok_fly1.builder.PostBuilder;
import com.mxdl.okhttp3.ok_fly1.call.RequestCall;
import com.mxdl.okhttp3.ok_fly1.response.OnResponse;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.concurrent.Executor;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Description: <FlyHttpUtils><br>
 * Author:      mxdl<br>
 * Date:        2020/5/14<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class FlyHttpUtils {
    private OkHttpClient okHttpClient;
    private MainUiTherad mainUiTherad = new MainUiTherad();

    class MainUiTherad implements Executor {
        Handler handler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable command) {
            handler.post(command);
        }
    }

    private FlyHttpUtils() {
        okHttpClient = new OkHttpClient.Builder().build();
    }

    public static FlyHttpUtils getInstance() {
        return FlyHttpUtilsHolder.flyHttpUtils;
    }

    public static class FlyHttpUtilsHolder {
        public static FlyHttpUtils flyHttpUtils = new FlyHttpUtils();
    }

    public <T> void execute(RequestCall requestCall, final OnResponse<T> onResponse) {
        requestCall.getCall().enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                onFail(e, onResponse);
            }

            @Override
            public void onResponse(Call call, final Response response) {
                try {
                    if (response.isSuccessful()) {
                        final String body = response.body().string();
                        Class<T> tClass = (Class<T>) ((ParameterizedType) onResponse.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                        final T t = new Gson().fromJson(body, tClass);
                        onSucc(t, onResponse);
                    } else {
                        onFail(new Exception("未知异常"), onResponse);
                    }
                } catch (final IOException e) {
                    onFail(e, onResponse);
                }
            }
        });
    }

    private <T> void onFail(final Exception e, final OnResponse<T> onResponse) {
        mainUiTherad.execute(new Runnable() {
            @Override
            public void run() {
                if (onResponse != null) {
                    onResponse.onFail(e);
                    onResponse.onComplete();
                }
            }
        });
    }

    private <T> void onSucc(final T t, final OnResponse<T> onResponse) {
        mainUiTherad.execute(new Runnable() {
            @Override
            public void run() {
                if (onResponse != null) {
                    onResponse.onSucc(t);
                    onResponse.onComplete();
                }
            }
        });
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public GetBuilder get() {
        return new GetBuilder();
    }

    public PostBuilder post() {
        return new PostBuilder();
    }
}
