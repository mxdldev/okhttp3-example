package com.mxdl.okhttp3.ok_chain.utils;

public interface IGenericsSerializator {
    <T> T transform(String response, Class<T> classOfT);
}
