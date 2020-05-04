package com.mxdl.okhttp3.response;


import com.mxdl.okhttp3.bean.User;

public class AddUserResponse extends BaseResponse{
    private User user;

    public AddUserResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AddUserResponse{" +
                "user=" + user +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
