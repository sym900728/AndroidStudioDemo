package com.sym.demogreendao;

import android.app.Application;

/**
 * Created by Sym on 2016/1/20.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DbManager.init(this);
    }
}
