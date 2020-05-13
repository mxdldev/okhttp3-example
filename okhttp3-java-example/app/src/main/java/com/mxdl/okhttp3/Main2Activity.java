package com.mxdl.okhttp3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mxdl.okhttp3.bean.User;

import java.io.IOException;

import javax.annotation.Nullable;
import javax.net.ssl.HandshakeCompletedListener;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class Main2Activity extends AppCompatActivity {

    private TextView txtContent;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtContent = findViewById(R.id.txt_content);
        handler = new Handler();
    }

    public void login(View view) {
        Log.v("MYTAG", "login start...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                //1.创建一个HttpClient
                OkHttpClient httpClient = new OkHttpClient.Builder().build();

                //2.创建一个request请求
                String url = "http://192.168.0.189:8080/user/login?username=mxdl&password=123456";
                Request loginRequst = new Request.Builder().url(url).build();

                //3.创建一个请求命令
                Call loginCall = httpClient.newCall(loginRequst);

                //4.发起一个同步的请求
                try {
                    Response loginResponse = loginCall.execute();
                    Log.v("MYTAG", loginResponse.body().string());
                } catch (IOException e) {
                    Log.v("MYTAG", e.toString());
                }
            }
        }).start();
    }

    public void addUser(View view) {
        Log.v("MYTAG", "addUser start...");
        //1.创建一个http客户端
        OkHttpClient httpClient = new OkHttpClient.Builder().build();

        //2.创建一个用户注册的请求
        User user = new User("zhangsan", 123);
        Request addUserRequest = new Request.Builder()
                .url("http://192.168.0.189:8080/user/addUser")
                .post(RequestBody.create(MediaType.parse("application/json;charset=utf-8"), new Gson().toJson(user)))
                .build();
        //3.创建一个请求命令
        Call addUserCall = httpClient.newCall(addUserRequest);

        //4.执行用户注册的命令
        addUserCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v("MYTAG", "onFail start...");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.v("MYTAG", "onResponse start...");
                final String content = response.body().string();
                Log.v("MYTAG", content);

                //更新UI操作
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        txtContent.setText(content);
                    }
                });
            }
        });
    }
}
