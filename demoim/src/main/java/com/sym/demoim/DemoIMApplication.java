package com.sym.demoim;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.alibaba.wxlib.util.SysUtil;
import com.sym.demoim.common.InitHelper;

/**
 * Created by xiaoming on 2016/3/18.
 */
public class DemoIMApplication extends MultiDexApplication {

    private static Context sContext;

    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (mustRunFirstInsideApplicationOnCreate()) {
            return;
        }

        //初始化云旺SDK
        InitHelper.initYWSDK(this);
    }

    private boolean mustRunFirstInsideApplicationOnCreate() {
        //必须的初始化
        SysUtil.setApplication(this);
        sContext = getApplicationContext();
        return SysUtil.isTCMSServiceProcess(sContext);
    }


}
