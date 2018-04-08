package com.forest.market.activity.user;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.forest.market.R;
import com.forest.market.activity.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

public class AboutUsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.view)
    View view;
    @BindView(R.id.iv_title_back)
    ImageView iv_title_back;
    @BindView(R.id.tv_title_name)
    TextView tv_title_name;
    @Override
    protected void initData() {
        iv_title_back.setVisibility(View.VISIBLE);
        iv_title_back.setImageResource(R.mipmap.nav_back_h);
        iv_title_back.setOnClickListener(this);
        tv_title_name.setText("关于我们");
    }
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(view).init();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_aboutus;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
        }
    }
}
