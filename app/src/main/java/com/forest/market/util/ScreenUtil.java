package com.forest.market.util;


import android.content.Context;

import com.forest.market.App;

/**
 * ScreenUtil  dp px 转换
 */
public class ScreenUtil {

    public static int dp2px(float dp){
        float density= App.getContext().getResources().getDisplayMetrics().density;
        return (int) (dp*density+0.5f);
    }
    /**
     * 获取屏幕高度(px)
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
    /**
     * 获取屏幕宽度(px)
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

//    public static int dp2px(Context context, float dp) {
//        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics()) + 0.5f);
//    }
}
