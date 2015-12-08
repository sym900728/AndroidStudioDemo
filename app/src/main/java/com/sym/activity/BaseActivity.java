package com.sym.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.sym.manager.ActivityManager;

public class BaseActivity extends Activity {

    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(BaseActivity.this, "BaseActivity has been created", Toast.LENGTH_SHORT).show();
    }

    protected void showToast(String message, int length) {
        Toast.makeText(this, message, length);
    }

    protected void finishActivity(String activityName) {
        Log.e(TAG, "finishActivity: " + activityName);
        ActivityManager.getInstance().remove(activityName);
    }

}
