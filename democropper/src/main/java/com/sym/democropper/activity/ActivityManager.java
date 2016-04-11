package com.sym.democropper.activity;

import android.app.Activity;
import android.util.Log;

import java.util.Stack;

/**
 * Created by Sym on 2016/1/22.
 */
public class ActivityManager {
    private final static String TAG = ActivityManager.class.getSimpleName();
    private static Stack activityStack;
    private static ActivityManager instance;

    public static ActivityManager getScreenManager(){
        if (instance == null) {
            instance = new ActivityManager();
        }
        Log.e(TAG, "ActivityManager create");
        return instance;
    }

    /**
     * 从栈中移除最后一个元??
     */
    public void popActivity(){
        Activity activity = currentActivity();
        if (activity != null) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
            activityStack.removeElement(activity);
            activity = null;
        }
        Log.i(TAG, "ScreenManager popActivity ");
    }

    /**
     * 从栈中移除指定的元素
     * @param activity
     */
    public void popActivity(Activity activity){
        if (activity != null) {
            Log.i(TAG, "要删除" + activity.getClass().getSimpleName());
            if (!activity.isFinishing()) {
                activity.finish();
                Log.i(TAG, activity.getClass().getSimpleName() + "正在关闭");
            } else {
                Log.i(TAG, activity.getClass().getSimpleName() + "已经关闭");
            }
            activityStack.removeElement(activity);
            Log.i(TAG, activity.getClass().getSimpleName() + "从栈中移除");
            activity = null;
        }
        int size=activityStack.size();
        Log.i(TAG, "删除后个数" + size);
    }

    /**
     * 将新元素添加到栈中
     * @param activity
     */
    public void pushActivity(Activity activity){
        if (activityStack == null) {
            activityStack = new Stack();
        }
        activityStack.addElement(activity);
        Log.e(TAG, "加入" + activity.getClass().getSimpleName());
        int size = activityStack.size();
        Log.e(TAG, "添加后个数" + size);
    }

    /** * 获取栈顶元素 * @return */
    public Activity currentActivity(){
        Activity activity = null;
        try {
            activity = (Activity) activityStack.lastElement();
        } catch (Exception e) {

        }
        return activity;
    }

    /**
     * 移除全部元素，除了指定类型的以外
     * @param cls
     */
    public void popAllActivityExceptOne(Class cls){
        int size = activityStack.size();
        for (int i = 0; i < size; i++) {
            Activity activity = (Activity) activityStack.get(i);
            if (activity != null && activity.getClass().equals(cls)) {

            } else {
                popActivity(activity);
                size--;
                i--;
            }
        }
    }

    /**
     * 按照给定的Class，结束一个Activity
     * @param cls
     */
    public void finishActivityByClass(Class cls){
        int size = activityStack.size();
        for (int i = 0; i < size; i++) {
            Activity activity = (Activity) activityStack.get(i);
            if (activity != null && activity.getClass().equals(cls)) {
                popActivity(activity);
                size--;
                i--;
            }
        }
    }

}
