package com.sym.demoretrofit.common;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xiaoming on 2016/4/7.
 * application interface
 */
public class Api {

    private static Api api;
    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;

    private Api() {}

    public synchronized static Api getInstance() {
        if (api == null) {
            api = new Api();
        }
        return api;
    }

    public void init() {
        mOkHttpClient = new OkHttpClient();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build();
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

}
