package com.example.demookhttp;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/2/29.
 */
public class Demo {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    String post(String url, String json) throws IOException {

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();

        Response response = client.newCall(request).execute();

        return response.body().string();
    }

    String bowlingJson(String player1, String player2) {
        return "{" + player1 + ":" + player2 + "}";
    }

    void test() {
        Demo demo = new Demo();
        String json = demo.bowlingJson("jake", "chris");
        String response = null;
        try {
            response = demo.post("http://www.roundsapp.com/post", json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(response);
    }

}
