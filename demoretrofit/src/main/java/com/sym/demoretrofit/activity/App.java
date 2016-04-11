package com.sym.demoretrofit.activity;

import android.app.Application;

import com.sym.demoretrofit.common.Api;

/**
 * Created by xiaoming on 2016/4/7.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化api（retrofit）
        Api.getInstance().init();
    }
}
