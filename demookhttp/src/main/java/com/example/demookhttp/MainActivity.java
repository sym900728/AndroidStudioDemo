package com.example.demookhttp;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();
    }

    private void init() {

        OkHttpClient okHttpClient = new OkHttpClient();
        Log.e(TAG, "connectTimeoutMillis() -- " + okHttpClient.connectTimeoutMillis());
        Log.e(TAG, "readTimeoutMillis() -- " + okHttpClient.readTimeoutMillis());
        Log.e(TAG, "writeTimeoutMillis() -- " + okHttpClient.writeTimeoutMillis());

        Request request = new Request.Builder()
                .get()
                .url("http://192.180.2.8:8080/MockServer/rest/UserService/login?username=wang&password=123")
                .build();
        okhttp3.Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "resonse body " + response.body().string().toString());
            }
        });

        //android.os.NetworkOnMainThreadException
        /*try {
            Response response = call.execute();

        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    private void test() {

    }

}
