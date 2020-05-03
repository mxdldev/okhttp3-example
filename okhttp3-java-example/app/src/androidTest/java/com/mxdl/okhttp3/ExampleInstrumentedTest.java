package com.mxdl.okhttp3;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.mxdl.okhttp3", appContext.getPackageName());
    }

    @Test
    public void test() {
        Log.v("MYTAG", "test start...===============================================");
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://www.baidu.com").build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            Log.v("MYTAG", response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() throws IOException {

        Request request = new Request.Builder().url("https://publicobject.com/helloworld.txt").build();
        Headers headers = request.headers();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }

            System.out.println(response.body().string());

            System.out.println("--------------------------------------------------");
            for (int i = 0; i < headers.size(); i++) {
                System.out.println(headers.name(i) + ":" + headers.value(i));
            }
            System.out.println("--------------------------------------------------");
        }

    }

    private final OkHttpClient client = new OkHttpClient();

    @Test
    public void test2() throws Exception {
        Log.v("MYTAG","test2 start.................................................");
        Request request = new Request.Builder().url("http://publicobject.com/helloworld.txt").build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    System.out.println(responseBody.string());
                }
            }
        });
    }

}
