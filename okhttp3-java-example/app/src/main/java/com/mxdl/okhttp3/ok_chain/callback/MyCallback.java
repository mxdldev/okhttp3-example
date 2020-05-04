package com.mxdl.okhttp3.ok_chain.callback;

import com.mxdl.okhttp3.ok_chain.base.BaseCallback;
import com.mxdl.okhttp3.ok_chain.utils.IGenericsSerializator;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import okhttp3.Response;
public abstract class MyCallback<T> extends BaseCallback<T> {
    IGenericsSerializator mGenericsSerializator;

    public MyCallback(IGenericsSerializator serializator) {
        mGenericsSerializator = serializator;
    }

    @Override
    public T parseNetworkResponse(Response response, int id) throws IOException {
        String string = response.body().string();
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (entityClass == String.class) {
            return (T) string;
        }
        T bean = mGenericsSerializator.transform(string, entityClass);
        return bean;
    }

}
