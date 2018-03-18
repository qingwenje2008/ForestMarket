package com.forest.market.activity.user;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.forest.market.R;
import com.forest.market.activity.MainActivity;
import com.forest.market.activity.base.BaseActivity;
import com.forest.market.util.ToastUtil;

import butterknife.BindView;

/**
 * Created by qingwenguo on 2018/3/18.
 */

public class MyInfoActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.view)
    View view;
    @BindView(R.id.iv_title_back)
    ImageView iv_title_back;
    @BindView(R.id.tv_title_name)
    TextView tv_title_name;


    @BindView(R.id.lin_icon)
    LinearLayout lin_icon;
    @BindView(R.id.img_icon)
    ImageView img_icon;
    @BindView(R.id.lin_name)
    LinearLayout lin_name;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_school)
    TextView tv_school;
    @BindView(R.id.tv_id)
    TextView tv_id;
    @Override
    protected void initData() {
        iv_title_back.setVisibility(View.VISIBLE);
        iv_title_back.setImageResource(R.mipmap.nav_back_h);
        iv_title_back.setOnClickListener(this);
        tv_title_name.setText("我的信息");
        lin_icon.setOnClickListener(this);
        lin_name.setOnClickListener(this);
    }
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(view).init();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_myinfo;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.lin_icon:
                finish();
                break;
            case R.id.lin_name:
                openActivity(UpdateNameActivity.class,null);
                break;



        }
    }
}
