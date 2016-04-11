package com.sym.demopicasso.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.sym.demopicasso.R;

public class MainActivity extends AppCompatActivity {

    private ImageView iv1, iv2, iv3, iv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
        iv4 = (ImageView) findViewById(R.id.iv4);
        this.init();

        /*AsyncHttp asyncHttp = new AsyncHttp();
        asyncHttp.get(new AsyncHttp.CallBack() {
            @Override
            public void success(String message) {
                //你的解析代码
            }

            @Override
            public void error(String message) {

            }
        });*/

    }

    private void init() {
        //Picasso.with(this).invalidate();
        Picasso.with(this).load("http://img2.3lian.com/img2007/19/33/001.jpg").
                resize(200, 200).into(iv1);
        Picasso.with(this).load("http://img2.3lian.com/img2007/19/33/002.jpg").resize(200, 200).into(iv2);
        Picasso.with(this).load("http://img2.3lian.com/img2007/19/33/003.jpg").resize(200, 200).into(iv3);
        Picasso.with(this).load("http://img2.3lian.com/img2007/19/33/004.jpg").resize(200, 200).into(iv4);
    }

}
