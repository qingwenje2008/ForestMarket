package com.forest.market.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.forest.market.R;
import com.forest.market.activity.user.MyInfoActivity;
import com.forest.market.fragment.base.BaseFragment;

import butterknife.BindView;


public class MineFragment extends BaseFragment implements View.OnClickListener{
    @BindView(R.id.tv_myInfo)
    TextView tv_myInfo;
    @BindView(R.id.tv_mySchool)
    TextView tv_mySchool;
    @BindView(R.id.tv_instrest)
    TextView tv_instrest;
    @BindView(R.id.tv_myFavourite)
    TextView tv_myFavourite;
    @BindView(R.id.tv_trRecord)
    TextView tv_trRecord;
    @BindView(R.id.tv_setting)
    TextView tv_setting;
    @BindView(R.id.iv_mine_icon)
    ImageView iv_mine_icon;

    @Override
    protected void initData() {
        tv_instrest.setOnClickListener(this);
        tv_myFavourite.setOnClickListener(this);
        tv_myInfo.setOnClickListener(this);
        tv_mySchool.setOnClickListener(this);
        tv_trRecord.setOnClickListener(this);
        tv_setting.setOnClickListener(this);
        iv_mine_icon.setOnClickListener(this);
    }

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.tv_myInfo:
                openActivity(MyInfoActivity.class,null);
                break;

            case R.id.tv_mySchool:
//                openActivity(LoginActivity.class);
                break;
            case R.id.tv_instrest:
//                openActivity(LoginActivity.class);
                break;
            case R.id.tv_myFavourite:
//                openActivity(LoginActivity.class);
                break;
            case R.id.tv_trRecord:
//                openActivity(LoginActivity.class);
                break;
            case R.id.tv_setting:
//                openActivity(SettingActivity.class,null);
                break;
            case R.id.iv_mine_icon:
//                openActivity(LoginActivity.class);
                break;
        }

    }
}
