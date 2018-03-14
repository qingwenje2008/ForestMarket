package com.forest.market.activity.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.inputmethod.InputMethodManager;

import com.forest.market.R;
import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.OSUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by guoqingwen on 17/1/9 上午11:28
 */

public abstract class BaseActivity extends AutoLayoutActivity {
    private FinishBroadcastReceiver receiver;
    public ImmersionBar mImmersionBar;
    private static final String NAVIGATIONBAR_IS_MIN = "navigationbar_is_min";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0){
            setContentView(getLayoutId());
        }else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }
        ButterKnife.bind(this);
        //初始化沉浸式
        if (isImmersionBarEnabled())
            initImmersionBar();
        className = getClass().getName();
        receiver = new FinishBroadcastReceiver();
        registerReceiver(receiver, new IntentFilter("com.rsd.finish"));
        initData();
        if (OSUtils.isEMUI3_1()) {
            //第一种
            getContentResolver().registerContentObserver(Settings.System.getUriFor
                    (NAVIGATIONBAR_IS_MIN), true, mNavigationStatusObserver);
            //第二种,禁止对导航栏的设置
            //mImmersionBar.navigationBarEnable(false).init();
        }
    }
    private ContentObserver mNavigationStatusObserver = new ContentObserver(new Handler()) {
        @Override
        public void onChange(boolean selfChange) {
            int navigationBarIsMin = Settings.System.getInt(getContentResolver(),
                    NAVIGATIONBAR_IS_MIN, 0);
            if (navigationBarIsMin == 1) {
                //导航键隐藏了
                mImmersionBar.transparentNavigationBar().init();
            } else {
                //导航键显示了
                mImmersionBar.navigationBarColor(android.R.color.black) //隐藏前导航栏的颜色
                        .fullScreen(false)
                        .init();
            }
        }
    };
    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.fullScreen(false); //有导航栏的情况下，activity全屏显示，也就是activity最下面被导航栏覆盖，不写默认非全屏

        mImmersionBar.init();
        mImmersionBar .statusBarColor(R.color.theme) ;    //状态栏颜色，不写默认透明色
    }
    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }
    protected void openActivity(Class<?> c, Bundle bundle) {
        Intent intent = new Intent(this, c);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void openActivity(Class<?> c) {
        openActivity(c, null);
    }

    protected void openActivityForResult(Class<?> c, int requestCode) {
        openActivityForResult(c, requestCode, null);
    }

    protected void openActivityForResult(Class<?> c, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, c);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    protected abstract void initData();

    protected abstract int getLayoutId();
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mImmersionBar.destroy();
        unregisterReceiver(receiver);

    }


    protected void hideKeyBroad(){
        ((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
    }


    protected void finishActivity(Class c) {
        Intent intent = new Intent("com.rsd.finish");
        intent.putExtra("name", c.getName());
        sendBroadcast(intent);
    }

    protected void finishAll() {
        Intent intent = new Intent("com.rsd.finish");
        intent.putExtra("name", "all");
        sendBroadcast(intent);
    }

    protected void requestPermission(String[] permissions){
        requestPermission(0,permissions);
    }

    protected void requestPermission(int requestCode,String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissions == null || permissions.length == 0) {
                return;
            }
            List<String> list = new ArrayList<>();
            for (int i = 0; i < permissions.length; i++) {
                if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                    list.add(permissions[i]);
                }
            }
            if (list.isEmpty()){
                onRequestPermissionsResult(requestCode,true);
            } else {
                for (String s : list) {
//                    if (shouldShowRequestPermissionRationale(s)){
//
//                    }
                }
                requestPermissions(list.toArray(new String[list.size()]), requestCode);
            }
        } else {
            onRequestPermissionsResult(requestCode,true);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean isSuccess=true;
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i]!=PackageManager.PERMISSION_GRANTED){
                isSuccess=false;
                break;
            }
        }
        onRequestPermissionsResult(requestCode,isSuccess);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    protected void onRequestPermissionsResult(int requestCode,boolean isSuccess){

    }
    private String className;

    protected class FinishBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("com.rsd.finish".equals(intent.getAction())) {
                String name = intent.getStringExtra("name");
                if (name.equals(className) || name.equals("all")) {
                    finish();
                }
            }
        }
    }
}
