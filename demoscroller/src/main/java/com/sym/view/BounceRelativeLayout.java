package com.sym.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by xiaoming on 2016/1/7.
 */
public class BounceRelativeLayout extends RelativeLayout{
    public BounceRelativeLayout(Context context) {
        super(context);
    }

    public BounceRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(11)
    public BounceRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
