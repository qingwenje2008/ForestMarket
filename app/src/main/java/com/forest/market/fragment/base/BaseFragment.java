package com.forest.market.fragment.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.autolayout.utils.AutoUtils;

import butterknife.ButterKnife;


public abstract class BaseFragment extends Fragment {
    protected Context context;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false);
            AutoUtils.auto(rootView);
            context = getContext();
        }
        ButterKnife.bind(this,rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (rootView != null && rootView.getParent() != null){
            ((ViewGroup)rootView.getParent()).removeView(rootView);
        }
    }

    protected void openActivity(Class<?> c, Bundle bundle){
        Intent intent = new Intent(context,c);
        if (null != bundle){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void openActivity(Class<?> c){
        openActivity(c,null);
    }

    protected abstract void initData();

    protected abstract int getLayoutId();

    protected void initView() {
    }
}
