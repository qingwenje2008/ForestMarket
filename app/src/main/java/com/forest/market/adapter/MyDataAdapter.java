package com.forest.market.adapter;

import android.content.Context;
import android.view.View;

import com.forest.market.adapter.base.RBaseAdapter;

/**
 * Created by qingwenguo on 2018/3/14.
 */

public class MyDataAdapter extends RBaseAdapter {
    public MyDataAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, Object item, int position, View parent) {

    }

    @Override
    public int getLayout(int viewType) {
        return 0;
    }

    @Override
    public ViewHolder getViewHolder(View view,int viewType) {
        return null;
    }
}
