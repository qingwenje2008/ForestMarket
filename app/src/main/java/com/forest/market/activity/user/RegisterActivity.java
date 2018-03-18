package com.forest.market.activity.user;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.forest.market.R;
import com.forest.market.activity.MainActivity;
import com.forest.market.activity.base.BaseActivity;
import com.forest.market.util.ToastUtil;

import butterknife.BindView;
/**
 * Created by guo on 17/2/28 上午10:02
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener
{
    @BindView(R.id.view)
    View view;
    @BindView(R.id.iv_title_back)
    ImageView iv_title_back;
    @BindView(R.id.tv_title_name)
    TextView tv_title_name;
    @BindView(R.id.tv_login)
    TextView tv_login;
    @BindView(R.id.tv_register)
    TextView tv_register;
    @Override
    protected void initData() {
        iv_title_back.setVisibility(View.VISIBLE);
        iv_title_back.setImageResource(R.mipmap.nav_back_h);
        iv_title_back.setOnClickListener(this);
        tv_title_name.setText("注册");
        tv_register.setOnClickListener(this);
        tv_login.setOnClickListener(this);

    }
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(view).init();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.tv_register:
                ToastUtil.showToast("注册成功");
                finish();
                openActivity(MainActivity.class,null);
                break;
            case R.id.tv_login:
                openActivity(LoginActivity.class,null);
                break;

        }

    }
}
