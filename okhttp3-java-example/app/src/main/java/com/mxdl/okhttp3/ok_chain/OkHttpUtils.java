package com.mxdl.okhttp3.ok_chain;

import android.os.Handler;
import android.os.Looper;

import com.ihsanbal.logging.LoggingInterceptor;
import com.mxdl.okhttp3.ok_chain.builder.GetBuilder;
import com.mxdl.okhttp3.ok_chain.builder.PostBuilder;
import com.mxdl.okhttp3.ok_chain.call.RequestCall;
import com.mxdl.okhttp3.ok_chain.base.BaseCallback;
import java.io.IOException;
import java.util.concurrent.Executor;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class OkHttpUtils {
    public static final long DEFAULT_MILLISECONDS = 10_000L;
    private volatile static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private MainThreadExecutor mPlatform;

    static class MainThreadExecutor implements Executor {
        private final Handler handler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable r) {
            handler.post(r);
        }
    }
    public OkHttpUtils(OkHttpClient okHttpClient) {
        if (okHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor.Builder().build()).build();
        } else {
            mOkHttpClient = okHttpClient;
        }

        mPlatform = new MainThreadExecutor();
    }


    public static OkHttpUtils initClient(OkHttpClient okHttpClient) {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtils(okHttpClient);
                }
            }
        }
        return mInstance;
    }

    public static OkHttpUtils getInstance() {
        return initClient(null);
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public static GetBuilder get() {
        return new GetBuilder();
    }

    public static PostBuilder postString() {
        return new PostBuilder();
    }

    public void execute(final RequestCall requestCall, BaseCallback baseCallback) {
        final BaseCallback finalBaseCallback = baseCallback;
        final int id = requestCall.getBaseRequest().getId();

        requestCall.getCall().enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                sendFailResultCallback(call, e, finalBaseCallback, id);
            }

            @Override
            public void onResponse(final Call call, final Response response) {
                try {
                    if (call.isCanceled()) {
                        sendFailResultCallback(call, new IOException("Canceled!"), finalBaseCallback, id);
                        return;
                    }

                    if (!response.isSuccessful()) {
                        sendFailResultCallback(call, new IOException("request failed , reponse's code is : " + response.code()), finalBaseCallback, id);
                        return;
                    }

                    Object o = finalBaseCallback.parseNetworkResponse(response, id);
                    sendSuccessResultCallback(o, finalBaseCallback, id);
                } catch (Exception e) {
                    sendFailResultCallback(call, e, finalBaseCallback, id);
                } finally {
                    if (response.body() != null)
                        response.body().close();
                }

            }
        });
    }


    public void sendFailResultCallback(final Call call, final Exception e, final BaseCallback baseCallback, final int id) {
        if (baseCallback == null) return;

        mPlatform.execute(new Runnable() {
            @Override
            public void run() {
                baseCallback.onFail(call, e, id);
                baseCallback.onComplete(id);
            }
        });
    }

    public void sendSuccessResultCallback(final Object object, final BaseCallback baseCallback, final int id) {
        if (baseCallback == null) return;
        mPlatform.execute(new Runnable() {
            @Override
            public void run() {
                baseCallback.onSucc(object, id);
                baseCallback.onComplete(id);
            }
        });
    }

    public void cancelTag(Object tag) {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }
}

