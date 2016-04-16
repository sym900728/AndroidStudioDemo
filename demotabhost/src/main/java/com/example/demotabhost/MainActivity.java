package com.example.demotabhost;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;

    //定义一个布局
    private LayoutInflater layoutInflater;

    private String mTextArray[] = {"首页", "消息", "好友"};
    private Class mFragmentArray[] = {HomeFragment.class, MessageFragment.class, FriendFragment.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();
    }

    private void init() {
        layoutInflater = LayoutInflater.from(this);
        this.initTabHost();
    }

    private void initTabHost() {

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        int count = mTextArray.length;
        for (int i = 0; i < count; i++) {
            //给每个 Tab 按钮设置图片、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextArray[i]).setIndicator(getTabHostItemView(i));
            //将 Tab 按钮添加进 Tab 选项卡中
            mTabHost.addTab(tabSpec, mFragmentArray[i], null);
            //设置 Tab 按钮的背景
            //mTabHost.getTabWidget().getChildAt(i).setBackgroundResource();
            mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }

    }

    private View getTabHostItemView(int index) {
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_item_iv);
        TextView textView = (TextView) view.findViewById(R.id.tab_item_tv);
        //imageView.setImageResource();
        textView.setText(mTextArray[index]);
        return view;
    }
}
