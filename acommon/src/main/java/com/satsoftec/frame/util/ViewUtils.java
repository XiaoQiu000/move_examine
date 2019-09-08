package com.satsoftec.frame.util;

import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.LinearLayout;

public class ViewUtils {

    public static void printView(String paramString, View paramView) {
        System.out.println(paramString + "=" + paramView);
        if (paramView == null)
            return;
        System.out.print("[" + paramView.getLeft());
        System.out.print(", " + paramView.getTop());
        System.out.print(", w=" + paramView.getWidth());
        System.out.println(", h=" + paramView.getHeight() + "]");
        System.out.println("mw=" + paramView.getMeasuredWidth() + ", mh=" + paramView.getMeasuredHeight());
        System.out.println("scroll [" + paramView.getScrollX() + "," + paramView.getScrollY() + "]");
    }

    /**
     * 根据手机的分辨率�? px(像素) 的单�? 转成�? dp
     */
    public static float scale = -1;

    public static float px2dipf(Context context, float pxValue) {
        if (scale == -1) {
            scale = context.getResources().getDisplayMetrics().density;
        }
        return px2dipf(scale, pxValue);
    }

    /**
     * 根据手机的分辨率�? dp 的单�? 转成�? px(像素)
     */
    public static float dip2pxf(Context context, float dpValue) {
        if (scale == -1) {
            scale = context.getResources().getDisplayMetrics().density;
        }
        return dip2pxf(scale, dpValue);
    }

    public static float px2dipf(float scale, float pxValue) {
        return (pxValue / scale + 0.5f);
    }

    public static float dip2pxf(float scale, float dpValue) {
        return (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率�? px(像素) 的单�? 转成�? dp
     */
    public static int px2dipi(Context context, float pxValue) {
        if (scale == -1) {
            scale = context.getResources().getDisplayMetrics().density;
        }
        return px2dipi(scale, pxValue);
    }

    /**
     * 根据手机的分辨率�? dp 的单�? 转成�? px(像素)
     */
    public static int dip2pxi(Context context, float dpValue) {
        if (scale == -1) {
            scale = context.getResources().getDisplayMetrics().density;
        }
        return dip2pxi(scale, dpValue);
    }

    public static int px2dipi(float scale, float pxValue) {
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2pxi(float scale, float dpValue) {
        return (int) (dpValue * scale + 0.5f);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getTypedValueResourceId(int resid, Context context) {
        TypedValue tv = new TypedValue();
        context.getTheme().resolveAttribute(resid, tv, true);
        return tv.resourceId;
    }


    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 将GridView改成单行横向布局
     */
    public static void changeGridView(int dataSize,Context context,GridView gv_record) {
        // item宽度
        int itemWidth = ViewUtils.dip2px(context, 100);
        // item之间的间隔
        int itemPaddingH = ViewUtils.dip2px(context, 1);
        // 计算GridView宽度
        int gridviewWidth = dataSize * (itemWidth + itemPaddingH);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        gv_record.setLayoutParams(params);
        gv_record.setColumnWidth(itemWidth);
        gv_record.setHorizontalSpacing(itemPaddingH);
        gv_record.setStretchMode(GridView.NO_STRETCH);
        gv_record.setNumColumns(dataSize);
    }

    public static void backgroundAlpha(Activity activity,float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }
}
