package com.example.demotabhost;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost mTabHost;

    //定义一个布局
    private LayoutInflater layoutInflater;

    private String textViewContent [] = {"首页" , "消息" , "好友" , "广场"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
