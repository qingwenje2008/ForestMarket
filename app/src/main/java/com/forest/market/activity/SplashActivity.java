package com.forest.market.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.forest.market.R;
import com.forest.market.activity.base.BaseActivity;
import com.forest.market.activity.user.LoginActivity;


/**
 * Created by guoqingwen on 2017/8/24.
 */

public class SplashActivity extends BaseActivity{
    private boolean isFirstIn;
    SharedPreferences preferences;


    @Override
    protected void initData() {
        //设置无标题


        preferences=getSharedPreferences("user", Context.MODE_PRIVATE);
        isFirstIn = preferences.getBoolean("is_first_open", true);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (isFirstIn){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("is_first_open", false);
                    editor.commit();
//                    openActivity(GuideActivity.class);
                    openActivity(LoginActivity.class);
                }else {
//
                    openActivity(LoginActivity.class);
                }
                finish();
            }
        },1000);
    }

    @Override
    protected int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash;
    }

    Handler handler=new Handler();

}
