package com.sym.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by xiaoming on 2016/1/5.
 */
public class DampScrollView extends ScrollView {

    private static final String TAG = "DampScrollView";

    public DampScrollView(Context context) {
        super(context);
    }

    public DampScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DampScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        this.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int[] location = new int[2];
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                //Log.e(TAG, "this.getScrollY():" + this.getScrollY());
                //this.getLocationOnScreen(location);
                //Log.e(TAG, "x:" + location[0]);
                //Log.e(TAG, "y:" + location[1]);
                //this.layout(0, 100, 0, 0);
                //this.getScrollY();
                //this.overScrollBy(0, 100, 0, -100, 0, 100, 0, 100, true);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //this.scrollTo();
        //Log.e(TAG, "this.getScrollY():" + this.getScrollY());

    }

    //@Override
    /*public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
        Log.e(TAG, "x:" + x + "   y:" + y);
        Log.e(TAG, "vvv");

        //Log.e(TAG, "scrollTo(x,y): (" + x + "," + y + ")");
    }*/

    /*@Override
    protected boolean overScrollBy(int deltaX, int deltaY,
                                   int scrollX, int scrollY,
                                   int scrollRangeX, int scrollRangeY,
                                   int maxOverScrollX, int maxOverScrollY,
                                   boolean isTouchEvent) {

        //Log.e(TAG, "deltaX:" + deltaX);
        Log.e(TAG, "deltaY:" + deltaY);
        //Log.e(TAG, "scrollX:" + scrollX);
        //Log.e(TAG, "scrollY:" + scrollY);
        //Log.e(TAG, "scrollRangeX:" + scrollRangeX);
        //Log.e(TAG, "scrollRangeY:" + scrollRangeY);
        //Log.e(TAG, "maxOverScrollX:" + maxOverScrollX);
        //Log.e(TAG, "maxOverScrollY:" + maxOverScrollY);
        //maxOverScrollY = 100;
        //this.getRootView().scrollTo(0, -200);//获取根 view
        return super.overScrollBy(deltaX, deltaY,
                scrollX, scrollY,
                scrollRangeX, scrollRangeY,
                maxOverScrollX, 400,
                isTouchEvent);
    }*/

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        Log.e(TAG, "scrollY:" + scrollY);
        int mScrollY = 0;
        if(this.getScrollY() == 0){
            mScrollY = - 100;
        } else {
            mScrollY = scrollY;
            //this.setScrollY(-100);
        }

        /*if (!this.isFinished()) {
            final int oldX = mScrollX;
            final int oldY = mScrollY;
            mScrollX = scrollX;
            mScrollY = scrollY;
            invalidateParentIfNeeded();
            onScrollChanged(mScrollX, mScrollY, oldX, oldY);
            if (clampedY) {
                mScroller.springBack(mScrollX, mScrollY, 0, 0, 0, getScrollRange());
            }
        } else {
            super.scrollTo(scrollX, scrollY);
        }

        awakenScrollBars();*/

        //this.scrollTo(0, 200);
        //this.scrollTo(0, -100);
        super.onOverScrolled(scrollX, mScrollY, clampedX, false);
        //Log.e(TAG, "onOverScrolled:" + scrollY);
        //if(this.getScrollY() == 0) {
        //   this.scrollTo(0, 400);
            //    this.layout(0, 720, 0, 100);
        //}
    }
}
