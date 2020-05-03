package com.mxdl.okhttp3.bean;

public class ResDTO<T> {
    private int code;
    private String msg;
    private T data;
    public static ResDTO onSucc(Object obj){
        ResDTO dto = new ResDTO();
        dto.setData(obj);
        return dto;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResDTO{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
