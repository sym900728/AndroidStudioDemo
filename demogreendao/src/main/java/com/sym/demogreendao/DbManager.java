package com.sym.demogreendao;

import android.content.Context;

import com.demogreendao.db.dao.DaoMaster;
import com.demogreendao.db.dao.DaoSession;

/**
 * Created by Sym on 2016/1/20.
 */
public class DbManager {

    private static DaoMaster daoMaster;
    private static DaoSession daoSession;


    public static void init(Context context) {

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "notes-db", null);
        daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public static DaoMaster getDaoMaster() {
        return daoMaster;
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
