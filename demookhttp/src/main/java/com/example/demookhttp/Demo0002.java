package com.example.demookhttp;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/4/11.
 */
public class Demo0002 {

    public void test() {
        //OkHttpClient okHttpClient = new OkHttpClient();

        /*OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //添加拦截器
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return null;
            }
        });

        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);


        OkHttpClient okHttpClient = builder.build();*/

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.connectTimeoutMillis();

    }
}
