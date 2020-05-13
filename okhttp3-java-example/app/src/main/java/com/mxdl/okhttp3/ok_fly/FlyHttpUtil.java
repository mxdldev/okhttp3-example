package com.mxdl.okhttp3.ok_fly;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.ihsanbal.logging.LoggingInterceptor;
import com.mxdl.okhttp3.ok_fly.builder.GetBuilder;
import com.mxdl.okhttp3.ok_fly.builder.PostBuilder;
import com.mxdl.okhttp3.ok_fly.call.RequestCall;
import com.mxdl.okhttp3.ok_fly.callback.RequestListener;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.concurrent.Executor;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;


/**
 * Description: <FlyHttpUtil><br>
 * Author:      mxdl<br>
 * Date:        2020/5/5<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class FlyHttpUtil {
    private OkHttpClient mHttpClient;
    private Executor mExecutor = new Executor() {
        Handler mHandler = new android.os.Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable command) {
            mHandler.post(command);
        }
    };

    private FlyHttpUtil() {
        mHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new LoggingInterceptor
                                .Builder()
                                .build()
                )
                .build();
    }

    public static FlyHttpUtil getInstance() {
        return FlyHttpUtilHolder.flyHttpUtil;
    }

    public OkHttpClient getHttpClient() {
        return mHttpClient;
    }

    public <T> void execute(final RequestCall requestCall, final RequestListener<T> listener) {
        if(listener != null){
            listener.onStart();
        }
        requestCall.getCall().enqueue(new Callback() {
            @Override
            public void onFailure( Call call,  IOException e) {
                sendFail(e, listener);
            }

            @Override
            public void onResponse( Call call,  Response response) throws IOException {
                if (call.isCanceled()) {
                    sendFail(new IOException("request cancel"), listener);
                    return;
                }
                if (!response.isSuccessful()) {
                    sendFail(new IOException("request fail code:" + response.code()), listener);
                    return;
                }
                String body = response.body().string();

                Class<T> tClass = (Class<T>) ((ParameterizedType) listener.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                T t = new Gson().fromJson(body, tClass);
                sendSucc(t, listener);
            }
        });
    }

    private void sendFail( final IOException e, final RequestListener listener) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if (listener != null) {
                    listener.onFail(e);
                    listener.onComplete();
                }
            }
        });
    }

    private <T> void sendSucc(final T t, final RequestListener<T> listener) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if (listener != null) {
                    listener.onSucc(t);
                    listener.onComplete();
                }
            }
        });
    }

    static class FlyHttpUtilHolder {
        static FlyHttpUtil flyHttpUtil = new FlyHttpUtil();
    }

    public GetBuilder get(){
        return new GetBuilder();
    }
    public PostBuilder post(){
        return new PostBuilder();
    }

}
