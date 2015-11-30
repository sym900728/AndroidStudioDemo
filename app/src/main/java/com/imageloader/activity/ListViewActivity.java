package com.imageloader.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sym.myapplication.R;
import com.imageloader.util.Constants;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.impl.ext.LruDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;
import java.io.IOException;

/**
 * Created by Sym on 2015/11/29.
 */
public class ListViewActivity extends Activity {

    private ListView listView;
    private MyBaseAdapter myBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        init();
    }

    private void init(){
        initData();
        listView = (ListView) findViewById(R.id.listview);
        myBaseAdapter = new MyBaseAdapter(this);
        listView.setAdapter(myBaseAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

    private void initData(){

        // 设置磁盘文件缓存目录
        //File cacheDir = Environment.getExternalStorageDirectory().getAbsoluteFile();
        File cacheDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/myApplication");
        // 设置 DisplayImageOptions
        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
                // 默认是 false（是否使用内存缓存）
                .cacheInMemory(true)
                // 默认是 false（是否使用磁盘缓存）
                .cacheOnDisk(true)
                // 设置 Bitmap 真色彩（.bitmapConfig(Bitmap.Config.RGB_565)）
                .bitmapConfig(Bitmap.Config.ARGB_8888).build();

        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(this);

        // 设置默认的 defaultDisplayImageOptions
        builder.defaultDisplayImageOptions(displayImageOptions);
        // 设置 memoryCache
        builder.memoryCache(new LruMemoryCache( 10 * 1024 * 1024));
        // 设置 memoryCache
        //builder.memoryCacheSize(2 * 1024 * 1024);
        // 设置 memoryCache
        //builder.memoryCacheSizePercentage(13);
        // default is device's screen width; device's screen height
        //builder.memoryCacheExtraOptions(720, 1280);

        // When you display an image in a small ImageView and later you try to display this image (from identical URI) in a larger ImageView
        // so decoded image of bigger size will be cached in memory as a previous decoded image of smaller size.
        // So the default behavior is to allow to cache multiple sizes of one image in memory.
        builder.denyCacheImageMultipleSizesInMemory();

        // Sets options for resizing/compressing of downloaded images before saving to disk cache.
        //builder.diskCacheExtraOptions();
        // 设置磁盘缓存（我这里设置的是不受限制的）
        //builder.diskCache(new UnlimitedDiskCache(cacheDir));//default

        // 设置磁盘缓存（这是设置的是受限制的）
        try {
            builder.diskCache(new LruDiskCache(cacheDir,new Md5FileNameGenerator(),100 * 1024 * 1024));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 设置磁盘缓存文件大小（我这里设置的是 50 MB）
        //builder.diskCacheSize(50 * 1024 * 1024);
        // 设置磁盘缓存文件的总个数
        //builder.diskCacheFileCount(10);
        // 设置磁盘缓存文件的名称
        //builder.diskCacheFileNameGenerator(new HashCodeFileNameGenerator());//default（使用 hashcode ）
        //builder.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        // 不知道这个参数是干什么的。
        builder.tasksProcessingOrder(QueueProcessingType.FIFO);
        // 设置线程的优先级别 Thread.MIN_PRIORITY（常数1），Thread.MAX_PRIORITY（常数10），Thread.NORM_PRIORITY（常数5）。
        // 其中每个线程的优先级都在Thread.MIN_PRIORITY到Thread.MAX_PRIORITY（常数10）之间
        // 默认值就是设置的 3
        builder.threadPriority(Thread.NORM_PRIORITY - 2);
        // 设置线程池的大小
        // 默认值设置的是 3（推荐是 1 - 5 ）
        builder.threadPoolSize(5);  // 1 - 5 is recommended.
        // If you set custom executor then following configuration options will not be considered for this executor:
        // threadPoolSize(int) threadPriority(int) tasksProcessingOrder(QueueProcessingType)
        // 自定义执行者
        /*builder.taskExecutor(new Executor() {
            @Override
            public void execute(Runnable command) {

            }
        });
        // 自定义执行者
        builder.taskExecutorForCachedImages(new Executor() {
            @Override
            public void execute(Runnable command) {

            }
        });*/
        //builder.imageDecoder(new BaseImageDecoder());

        //builder.imageDownloader(new BaseImageDownloader());

        // 设置写 debug logs（发布的时候移除点这一句）
        builder.writeDebugLogs();

        ImageLoaderConfiguration config = builder.build();
        ImageLoader.getInstance().init(config);

        DiskCache diskCache = ImageLoader.getInstance().getDiskCache();
        Log.e("diskCache.getDirectory", diskCache.getDirectory().getAbsolutePath());

    }

    private class MyBaseAdapter extends BaseAdapter {

        private LayoutInflater inflater;

        public MyBaseAdapter(Context context){
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return 40;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.e("debug", String.valueOf(position));
            ViewHolder viewHolder;

            if(convertView == null){
                convertView = inflater.inflate(R.layout.listivew_item, null);
                viewHolder = new ViewHolder();
                viewHolder.listview_item_iv = (ImageView) convertView.findViewById(R.id.listview_item_iv);
                viewHolder.listview_item_tv = (TextView) convertView.findViewById(R.id.listview_item_tv);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            ImageLoader.getInstance().displayImage(Constants.images[position], viewHolder.listview_item_iv);
            viewHolder.listview_item_tv.setText("This is just test!");
            return convertView;
        }

        private class ViewHolder{
            ImageView listview_item_iv;
            TextView listview_item_tv;
        }
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

}
