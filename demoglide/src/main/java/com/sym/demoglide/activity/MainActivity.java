package com.sym.demoglide.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sym.demoglide.R;

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

    }

    private void init() {
        Glide.with(this).load("http://img2.3lian.com/img2007/19/33/001.jpg").into(iv1);

        String url = "";
        Glide.with(this).load(url).into(iv2);
        //GlideBuilder glideBuilder = new GlideBuilder(this);
        //glideBuilder.setDiskCache(new InternalCacheDiskCacheFactory(this, 10 * 1024 * 1024));
        //glideBuilder.setDiskCache(new ExternalCacheDiskCacheFactory(this, 10 * 1024 * 1024));
        //glideBuilder.setMemoryCache(new LruResourceCache(2 * 1024 * 1024));
        //Glide.setup(glideBuilder);
    }
}
