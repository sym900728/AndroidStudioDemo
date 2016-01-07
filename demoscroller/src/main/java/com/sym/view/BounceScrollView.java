package com.sym.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by xiaoming on 2016/1/7.
 */
public class BounceScrollView extends ScrollView {

    private static final String TAG = "BounceScrollView";


    public BounceScrollView(Context context) {
        super(context);
    }

    public BounceScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BounceScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isGetTop() {
        if (this.getScrollY() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isGetBottom() {
        if (getChildCount() == 0) {
            return true;
        }
        if (getScrollY() >= (getChildAt(0).getHeight() - getMeasuredHeight())) {
            return true;
        } else {
            return  false;
        }
    }

}
