package com.forest.market.presenter;


import android.content.Context;


public abstract class Presenter {


    public void showLoginDialog(Context context) {
        if (context!=null) {
//            LoginDialog.getInstance(context).show();
        }
    }

    public abstract void onDestroy();


}
