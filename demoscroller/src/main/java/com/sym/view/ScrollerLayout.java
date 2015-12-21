package com.sym.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * Created by Administrator on 2015/12/21.
 */
public class ScrollerLayout extends RelativeLayout {

    private Scroller scroller;
    private Context context;
    private boolean isOpen = false;
    private boolean isMoving = false;
    private int width, height;
    private int animationDuration = 1000;

    private static final String TAG = "ScrollerLayout";

    public ScrollerLayout(Context context) {
        super(context);
        init(context);
    }

    public ScrollerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        this.scroller = new Scroller(context);
    }

    public void open() {
        if(!isMoving){

            Log.e(TAG, "finalX:" + scroller.getFinalX());
            Log.e(TAG, "finalY:" + scroller.getFinalY());

            //Log.e(TAG, "height:" + this.getHeight());
            //Log.e(TAG, "width:" + this.getWidth());
            this.width = this.getWidth();
            this.height = this.getHeight();

            this.setVisibility(View.VISIBLE);
            this.scrollTo(0, height);
            this.isOpen = true;
            this.scroller.startScroll(0, height, 0, -height, animationDuration);
            this.invalidate();
        }
    }

    public void close() {
        if(!isMoving){
            this.isOpen = false;
            this.scroller.startScroll(0, 0, 0, height, animationDuration);
            this.invalidate();
        }
    }

    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public void computeScroll() {
        //先判断mScroller滚动是否完成
        if (scroller.computeScrollOffset()) {

            //这里调用View的scrollTo()完成实际的滚动
            this.scrollTo(scroller.getCurrX(), scroller.getCurrY());
            //Log.e(TAG, "finish:" + scroller.getFinalY());
            //Log.e(TAG, "mmm:" + scroller.getCurrY());


            //必须调用该方法，否则不一定能看到滚动效果
            postInvalidate();
            isMoving = true;
        } else {
            isMoving = false;
            Log.e(TAG, "currY:" + scroller.getCurrY());
            if(!isOpen){
                this.setVisibility(View.INVISIBLE);
            }
        }
        super.computeScroll();
    }


}
