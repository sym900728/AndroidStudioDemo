package com.sym.manager;

import android.app.Activity;
import android.util.Log;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sym on 2015/12/6.
 */
public class ActivityManager {

    private static ActivityManager instance;
    private static final String TAG = "ActivityManager";
    private static Map<String, SoftReference<Activity>> map = new HashMap<String, SoftReference<Activity>>();

    private ActivityManager() {
    }

    public synchronized static ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    public void add(Activity activity) {
        SoftReference<Activity> softReference = new SoftReference<Activity>(activity);
        map.put(activity.getLocalClassName(), softReference);
    }

    public void remove(String key) {
        SoftReference<Activity> softReference = map.get(key);
        Log.e(TAG, "key is :" + key);
        if (softReference != null) {
            Log.e(TAG, key);
            softReference.get().finish();
            map.remove(key);
        }
    }

    public Activity get(String key) {
        return map.get(key).get();
    }

}
