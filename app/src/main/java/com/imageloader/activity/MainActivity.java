package com.imageloader.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sym.myapplication.R;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button test1, test2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        test1 = (Button) findViewById(R.id.main_test1_btn);
        test1.setOnClickListener(this);
        test2 = (Button) findViewById(R.id.main_test2_btn);
        test2.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_test1_btn:
                Toast.makeText(MainActivity.this, "go to ListViewActivity", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.main_test2_btn:
                break;
        }
    }
}
