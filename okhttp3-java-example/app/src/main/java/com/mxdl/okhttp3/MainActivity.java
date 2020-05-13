package com.mxdl.okhttp3;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mxdl.okhttp3.bean.User;
import com.mxdl.okhttp3.ok_fly.FlyHttpUtil;
import com.mxdl.okhttp3.ok_fly.callback.RequestListener;
import com.mxdl.okhttp3.ok_man.OkHttpManager;
import com.mxdl.okhttp3.ok_man.response.OnResponse;
import com.mxdl.okhttp3.ok_chain.OkHttpUtils;
import com.mxdl.okhttp3.ok_chain.callback.MyCallback;
import com.mxdl.okhttp3.ok_chain.utils.JsonGenericsSerializator;
import com.mxdl.okhttp3.ok_chain.utils.L;
import com.mxdl.okhttp3.response.AddUserResponse;
import com.mxdl.okhttp3.response.BaseResponse;


import java.io.IOException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_get).setOnClickListener(this);
        findViewById(R.id.btn_get1).setOnClickListener(this);
        findViewById(R.id.btn_get2).setOnClickListener(this);
        findViewById(R.id.btn_post).setOnClickListener(this);
        findViewById(R.id.btn_post1).setOnClickListener(this);
        findViewById(R.id.btn_post2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get:
                login();
                break;
            case R.id.btn_post:
                addUser();
                break;
            case R.id.btn_get1:
                login1();
                break;
            case R.id.btn_post1:
                addUser1();
                break;
            case R.id.btn_get2:
                //login2();
                FlyHttpUtil.getInstance().get()
                        .url("http://192.168.31.105:8080/user/login")
                        .addParam("userName","mxdl")
                        .addParam("passWord","123456")
                        .buildCall()
                        .execute(new RequestListener<BaseResponse>() {
                            @Override
                            public void onStart() {
                                Log.v("MYTAG","onStart start...");
                            }

                            @Override
                            public void onSucc(BaseResponse response) {
                                Log.v("MYTAG","onSucc start...");
                                Log.v("MYTAG",response.toString());
                            }

                            @Override
                            public void onFail(Exception e) {
                                Log.v("MYTAG","onFail start...");
                                Log.v("MYTAG",e.toString());
                            }

                            @Override
                            public void onComplete() {
                                Log.v("MYTAG","onComplete start...");
                            }
                        });
                break;
            case R.id.btn_post2:
                FlyHttpUtil.getInstance().post()
                        .url("http://192.168.31.105:8080/user/addUser")
                        .body(new User("aaa",111))
                        .buildCall()   
                        .execute(new RequestListener<AddUserResponse>() {
                            @Override
                            public void onStart() {
                                Log.v("MYTAG","onStart...");
                            }

                            @Override
                            public void onSucc(AddUserResponse addUserResponse) {
                                Log.v("MYTAG","onSucc...");
                                Log.v("MYTAG",addUserResponse.toString());
                            }

                            @Override
                            public void onFail(Exception e) {
                                Log.v("MYTAG","onFail...");
                                Log.v("MYTAG",e.toString());
                            }

                            @Override
                            public void onComplete() {
                                Log.v("MYTAG","onComplete...");
                            }
                        });

                //addUser2();
                break;
        }
    }

    /**
     * 原生的get请求
     */
    private void login() {
        //创建个http客户端
        OkHttpClient client = new OkHttpClient();
        //构建一个url
        HttpUrl httpUrl = HttpUrl.parse("http://192.168.31.105:8080/user/login").newBuilder()
                .addQueryParameter("userName", "mxdl")
                .addQueryParameter("passWord", "123456").build();
        //根据url创建一个request
        Request loginRequest = new Request.Builder().url(httpUrl).build();

        //根据request创建一个call
        Call call = client.newCall(loginRequest);

        //开启一个异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v("MYTAG", "onFailure start...");
                Log.v("MYTAG", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.v("MYTAG", "onSucc start...");
                Log.v("MYTAG", response.body().string());
            }
        });
    }

    /**
     * 原生的post请求
     */
    private void addUser() {
        //创建一个http客户端
        OkHttpClient client = new OkHttpClient();
        //构建一个request请求
        final Request request = new Request.Builder().url("http://192.168.31.105:8080/user/addUser")
                .post(RequestBody.create(MediaType.parse("application/json;charset=utf-8"),"{\"userName\":\"mxdl\",\"passWord\":123456}"))
                .build();
        //根据request创建一个call命令
        Call call = client.newCall(request);
        //执行call命令
        call.enqueue(new Callback() {
            @Override
            public void onFailure( Call call,  IOException e) {
                Log.v("MYTAG", "onFail start...");
                Log.v("MTTAG", e.toString());
            }

            @Override
            public void onResponse( Call call,  Response response) throws IOException {
                Log.v("MYTAG", "onSucc start...");
                Log.v("MYTAG", response.body().string());
            }
        });
    }

    /**
     * OkHttpManager的get请求
     */
    private void login1() {
        //把登录的参数放在HashMap里
        HashMap<String, String> params = new HashMap<>();
        params.put("userName", "mxdl");
        params.put("passWord", "123456");

        //传入url，参数，回调开始请求
        OkHttpManager.getInstance().get("http://192.168.31.105:8080/user/login", params, new OnResponse<BaseResponse>() {
            @Override
            public void onStart() {
                Log.v("MYTAG", "onStart...");
            }

            @Override
            public void onSucc(BaseResponse s) {
                Log.v("MYTAG", "onSucc...");
                Log.v("MYTAG", s.toString());
            }

            @Override
            public void onFail(Exception e) {
                Log.v("MYTAG", "onFail...");
                Log.v("MYTAG", e.toString());
            }

            @Override
            public void onComplete() {
                Log.v("MYTAG", "onComplete...");
            }
        });
    }

    /**
     * 封装的post的请求
     */
    private void addUser1() {
        //创建要提交的User对象
        User user = new User("mxdl", 111);

        //传入url，User和回调开启添加一个用户的请求
        OkHttpManager.getInstance().post("http://192.168.31.105:8080/user/addUser", user, new OnResponse<AddUserResponse>() {
            @Override
            public void onStart() {
                Log.v("MYTAG", "onStart...");
            }

            @Override
            public void onSucc(AddUserResponse resDTO) {
                Log.v("MYTAG", "onSucc...");
                Log.v("MYTAG", resDTO.toString());
                Log.v("MYTAG", resDTO.toString());
            }

            @Override
            public void onFail(Exception e) {
                Log.v("MYTAG", "onFail...");
                Log.v("MYTAG", e.toString());
            }

            @Override
            public void onComplete() {
                Log.v("MYTAG", "onComplete...");
            }
        });
    }

    /**
     * 链式调用的get请求
     */
    private void login2() {
        OkHttpUtils.get()
                .url("http://192.168.31.105:8080/user/login")
                .addParams("userName", "mxdl")
                .addParams("passWord", "123456")
                .build()
                .execute(new MyCallback<BaseResponse>(new JsonGenericsSerializator()) {
                    @Override
                    public void onStart(Request request, int id) {
                        L.e("onStart start...");
                    }

                    @Override
                    public void onFail(Call call, Exception e, int id) {
                        L.e("onFail start...");
                    }

                    @Override
                    public void onSucc(BaseResponse response, int id) {
                        L.e("onSucc start...");
                        L.e(response.toString());
                    }

                    @Override
                    public void onComplete(int id) {
                        L.e("onComplete start...");
                    }
                });
    }

    /**
     * 链式调用的post请求
     */
    private void addUser2() {
        OkHttpUtils.postString()
                .url("http://192.168.31.105:8080/user/addUser")
                .content(new User("aaa", 111))
                .mediaType(MediaType.parse("application/json;charset=utf-8"))
                .build()
                .execute(new MyCallback<AddUserResponse>(new JsonGenericsSerializator()) {
                    @Override
                    public void onFail(Call call, Exception e, int id) {
                        L.e("onFail start...");
                        L.e(e.toString());
                    }

                    @Override
                    public void onSucc(AddUserResponse response, int id) {
                        L.e("onSucc start...");
                        L.e(response.toString());
                    }
                });
    }
}
