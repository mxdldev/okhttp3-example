package com.mxdl.okhttp3.ok_man.response;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Description: <MyCallBack><br>
 * Author:      mxdl<br>
 * Date:        2020/5/2<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class MyCallBack<T> implements Callback {
    private static final int ON_START = 0;
    private static final int ON_SUCC = 1;
    private static final int ON_FAIL = 2;
    private static final int ON_COMPLETE = 3;
    private OnResponse<T> mOnResponse;
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case ON_START:
                    if (mOnResponse != null) {
                        mOnResponse.onStart();
                    }
                    break;
                case ON_SUCC:
                    if (mOnResponse != null) {
                        try {
                            String body = (String) msg.obj;
                            //ResDTO<T> data = new Gson().fromJson(body, getType());
                            T data = (T) new Gson().fromJson(body, getTClass());
                            mOnResponse.onSucc(data);
                        } catch (Exception e) {
                            mOnResponse.onFail(e);
                        }
                        mOnResponse.onComplete();
                    }
                    break;
                case ON_FAIL:
                    if (mOnResponse != null) {
                        mOnResponse.onFail(msg.obj != null ? (Exception) msg.obj : new Exception());
                        mOnResponse.onComplete();
                    }
                    break;
                case ON_COMPLETE:
                    if (mOnResponse != null) {
                        mOnResponse.onComplete();
                    }
                    break;
            }
        }
    };


    public MyCallBack(OnResponse<T> onResponse) {
        mOnResponse = onResponse;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        sendMessage(ON_FAIL, e);
    }

    private void sendMessage(int what, Object e) {
        Message message = new Message();
        message.what = what;
        message.obj = e;
        mHandler.sendMessage(message);
    }

    @Override
    public void onResponse(Call call, Response response) {
        try {
            if (response.isSuccessful()) {
                String string = response.body().string();
                sendMessage(ON_SUCC, string);
            } else {
                sendMessage(ON_FAIL, new Exception("未知异常"));
            }
        } catch (Exception e) {
            sendMessage(ON_FAIL, e);
        }
    }


    public void onStart() {
        sendMessage(ON_START, "");
    }


    public Class getTClass() {
        Class<T> tClass = (Class<T>) ((ParameterizedType) mOnResponse.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }

//    
//    private Type getType() {
//        Type type = mOnResponse.getClass().getGenericSuperclass();
//        Type[] types = ((ParameterizedType) type).getActualTypeArguments();
//        return new MyParameterizedType(ResDTO.class, new Type[]{types[0]});
//    }
}
