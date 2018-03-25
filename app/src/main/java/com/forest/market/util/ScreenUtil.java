package com.forest.market.util;


import com.forest.market.App;

/**
 * ScreenUtil  dp px 转换
 */
public class ScreenUtil {

    public static int dp2px(float dp){
        float density= App.getContext().getResources().getDisplayMetrics().density;
        return (int) (dp*density+0.5f);
    }
}
