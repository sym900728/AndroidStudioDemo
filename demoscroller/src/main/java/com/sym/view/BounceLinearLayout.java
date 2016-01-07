package com.sym.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by xiaoming on 2016/1/7.
 */
public class BounceLinearLayout extends LinearLayout {

    private float mDownY;
    private float friction = 0.55f;

    private BounceScrollView mScrollView;


    private static final String TAG = "BounceLinearLayout";

    public BounceLinearLayout(Context context) {
        super(context);
    }

    public BounceLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(11)
    public BounceLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        Log.e(TAG, "");
        if(child instanceof BounceScrollView && mScrollView == null) {
            this.mScrollView = (BounceScrollView) child;
            this.mScrollView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        }
        super.addView(child, index, params);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float distanceY = mDownY - event.getY();
                if (this.mScrollView.isGetTop()) {
                    handleScrollY(distanceY);
                }
                if (this.mScrollView.isGetBottom()) {
                    handleScrollY(distanceY);
                }
                break;
            case MotionEvent.ACTION_UP:
                handleActionUp();
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    /**
     *
     */
    private void handleActionUp() {
        this.scrollTo(0, 0);
    }

    private void handleScrollY(float distanceY) {
        this.scrollTo(0, (int)(distanceY * (1 - this.friction)));
    }

    /**
     * set friction (the value between 0 and 1)
     * @param friction
     */
    public void setFriction(float friction) {
        if(friction >= 0 && friction <= 1) {
            this.friction = friction;
        }
    }

    /**
     * get friction
     * @return
     */
    public float getFriction() {
        return this.friction;
    }
}
