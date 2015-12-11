package com.sym.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sym.myapplication.R;
import com.sym.manager.ActivityManager;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private Button test1, test2, test3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityManager.getInstance().add(this);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void init() {
        test1 = (Button) findViewById(R.id.main_test1_btn);
        test1.setOnClickListener(this);
        test2 = (Button) findViewById(R.id.main_test2_btn);
        test2.setOnClickListener(this);
        findViewById(R.id.main_test3_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_test1_btn:
                showToast("vvvvvvvvvvv", Toast.LENGTH_LONG);
                Toast.makeText(MainActivity.this, "go to TestUniversalImageLoaderListViewActivity", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, TestUniversalImageLoaderListViewActivity.class);
                startActivity(intent);
                finishActivity(this.getLocalClassName());
                break;
            case R.id.main_test2_btn:
                showToast("hahahahha", Toast.LENGTH_SHORT);
                Toast.makeText(MainActivity.this, "go to TestAndroidAsyncHttpActivity", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(MainActivity.this, TestAndroidAsyncHttpActivity.class);
                startActivity(intent2);
                break;
            case R.id.main_test3_btn:
                Intent intent3 = new Intent(MainActivity.this, TestRubberIndicatorActivity.class);
                startActivity(intent3);
                break;
        }
    }

    @Override
    protected void finishActivity(String activityName) {
        Log.e(TAG, "ok");
        Log.e(TAG, activityName);
        super.finishActivity(activityName);
    }

}
