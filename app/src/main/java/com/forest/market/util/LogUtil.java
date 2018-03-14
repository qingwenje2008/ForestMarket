package com.forest.market.util;

import android.util.Log;

/**
 * Created by guoqingwen on 2016/07/28 14:04
 */
public class LogUtil {
    private static final String TAG = "rsd";

    public static void i(String msg) {
        Log.i(TAG, msg);
    }

    public static void i(String tag,String msg){
        Log.i(tag,msg);
    }
    public static void e(String tag,String msg){
        Log.e(tag,msg);
    }
    public static void w(String tag,String msg){
        Log.w(tag,msg);
    }
    public static void d(String tag,String msg){
        Log.d(tag,msg);
    }
    public static void v(String tag,String msg){
        Log.v(tag,msg);
    }
}
