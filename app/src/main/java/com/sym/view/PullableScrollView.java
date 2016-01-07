/**
 * Copyright 2015 Pengyuan-Jiang
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p/>
 * Author：Ybao on 2015/11/3 ‏‎11:43
 * <p/>
 * QQ: 392579823
 * <p/>
 * Email：392579823@qq.com
 */
package com.sym.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class PullableScrollView extends ScrollView implements Pullable {

    private ScrollViewListener scrollViewListener;

    private float verticalScrollSpeed;

    public PullableScrollView(Context context) {
        super(context);
    }

    public PullableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullableScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public float getVerticalScrollSpeed() {
        return this.verticalScrollSpeed;
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        calculateVerticalScrollSpeed(t, oldt);
        super.onScrollChanged(l, t, oldl, oldt);
        if(this.scrollViewListener != null) {
            scrollViewListener.onScrollChanged(l, t, oldl, oldt);
        }
    }

    private void calculateVerticalScrollSpeed(int t, int oldt) {
        float originalVerticalScrollSpeed = (float) (t - oldt) / 0.017f;;
        this.verticalScrollSpeed = originalVerticalScrollSpeed / 50;
    }


    @Override
    protected int computeVerticalScrollOffset() {
        return super.computeVerticalScrollOffset();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public boolean isGetTop() {
        if (getScrollY() <= 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean isGetBottom() {
        if (getChildCount() == 0) {
            return true;
        }
        if (getScrollY() >= (getChildAt(0).getHeight() - getMeasuredHeight()))
            return true;
        else
            return false;
    }

    public interface ScrollViewListener {
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }
}
