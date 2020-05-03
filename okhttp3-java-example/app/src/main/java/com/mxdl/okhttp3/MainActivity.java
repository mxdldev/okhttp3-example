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
import okhttp3.internal.platform.Platform;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.mxdl.okhttp3.bean.ResDTO;
import com.mxdl.okhttp3.bean.User;
import com.mxdl.okhttp3.http.OkHttpManager;
import com.mxdl.okhttp3.http.OnResponse;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    private final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor.Builder().setLevel(Level.BODY)
            .log(Platform.INFO)
            .request("request")
            .response("reponse")
            .build()).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //login();
                HashMap<String, String> params = new HashMap<>();
                params.put("userName", "mxdl");
                params.put("passWord", "123456");
                OkHttpManager.getInstance().get("http://192.168.31.105:8080/user/login", params, new OnResponse<ResDTO>() {
                    @Override
                    public void onStart() {
                        Log.v("MYTAG", "onStart...");
                    }

                    @Override
                    public void onSucc(ResDTO s) {
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
        });
        findViewById(R.id.btn_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpManager.getInstance().post("http://192.168.31.105:8080/user/addUser", new User("mxdl", 111), new OnResponse<User>() {
                    @Override
                    public void onStart() {
                        Log.v("MYTAG", "onStart...");
                    }

                    @Override
                    public void onSucc(ResDTO<User> resDTO) {
                        Log.v("MYTAG", "onSucc...");
                        Log.v("MYTAG", resDTO.toString());
                        Log.v("MYTAG", resDTO.getData().toString());

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
        });
    }

    private void postTest() {
        final Request request = new Request.Builder().url("http://192.168.31.105:8080/user/addUser")
                .post(RequestBody.create("{\"userName\":\"mxdl\",\"passWord\":123456}", MediaType.parse("application/json;charset=utf-8")))
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.v("MYTAG", "onFail start...");
                Log.v("MTTAG", e.toString());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.v("MYTAG", "onResponse start...");
                Log.v("MYTAG", response.body().string());
            }
        });
    }

    private void login() {
        //登录Get请求
        HttpUrl httpUrl = HttpUrl.parse("http://192.168.31.105:8080/user/login").newBuilder()
                .addQueryParameter("userName", "mxdl")
                .addQueryParameter("passWord", "123456").build();
        Request loginRequest = new Request.Builder().url(httpUrl).build();

        client.newCall(loginRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v("MYTAG", "onFailure start...");
                Log.v("MYTAG", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.v("MYTAG", "onResponse start...");
                Log.v("MYTAG", response.body().string());
            }
        });
    }


}
