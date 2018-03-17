package com.forest.market.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.forest.market.R;
import com.forest.market.fragment.base.BaseFragment;

import butterknife.BindView;


public class MineFragment extends BaseFragment implements View.OnClickListener{



    @BindView(R.id.iv_mine_icon)
    ImageView iv_mine_icon;

    @Override
    protected void initData() {



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

            case R.id.tv_setting:
//                openActivity(SettingActivity.class,null);
                break;

            case R.id.iv_mine_icon:
//                openActivity(LoginActivity.class);
                break;
        }

    }
}
