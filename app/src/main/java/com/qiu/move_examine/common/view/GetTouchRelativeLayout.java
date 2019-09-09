package com.qiu.move_examine.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * @author Mr.Qiu
 * @date 2017/12/14
 */

public class GetTouchRelativeLayout extends RelativeLayout {
    public GetTouchRelativeLayout(Context context) {
        super(context);
    }

    public GetTouchRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GetTouchRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
