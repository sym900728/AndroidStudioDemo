package com.sym.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.example.sym.myapplication.R;


public class GuideActivity extends Activity {

    private Button testBtn;

    private static final String TAG = "GuideActivity";

    private static final int TEST = 0;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TEST:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridue);
        this.init();
    }

    private void init() {
        testBtn = (Button) findViewById(R.id.test);
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testThread();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private void testThread() {
        new Thread(){
            @Override
            public void run() {
                super.run();

                try {
                    sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.sendEmptyMessage(TEST);

            }
        }.start();
    }


}
