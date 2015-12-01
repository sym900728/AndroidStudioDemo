package com.sym.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.sym.myapplication.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class TestAndroidAsyncHttpActivity extends Activity implements View.OnClickListener {

    private Button btn_test1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_android_async_http_activity);
        init();
    }

    private void init() {
        btn_test1 = (Button) findViewById(R.id.btn_test1);
        btn_test1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test1:
                test1();
                break;
        }
    }

    private void test1() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://img2.3lian.com/img2007/19/33/001.jpg", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, cz.msebera.android.httpclient.Header[] headers, byte[] bytes) {

                Log.e("AsyncHttp", String.valueOf(bytes.length));

            }

            @Override
            public void onFailure(int i, cz.msebera.android.httpclient.Header[] headers, byte[] bytes, Throwable throwable) {

            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                super.onProgress(bytesWritten, totalSize);
                Log.e("AsyncHttpProgress", String.valueOf(bytesWritten) + "<---->" + String.valueOf(totalSize));

            }
        });

    }
}
