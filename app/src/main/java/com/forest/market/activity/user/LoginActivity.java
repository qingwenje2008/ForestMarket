package com.forest.market.activity.user;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.forest.market.R;
import com.forest.market.activity.MainActivity;
import com.forest.market.activity.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by guoqingwen on 17/2/28 上午10:02
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener
{

    @BindView(R.id.iv_title_back)
    ImageView iv_title_back;
    @BindView(R.id.tv_title_name)
    TextView tv_title_name;
    @BindView(R.id.tv_register)
    TextView tv_register;
    @BindView(R.id.tv_forget_pass)
    TextView tv_forget_pass;
    @BindView(R.id.tv_login)
    TextView tv_login;
    @Override
    protected void initData() {
        iv_title_back.setVisibility(View.VISIBLE);
        iv_title_back.setImageResource(R.mipmap.nav_back_h);
        iv_title_back.setOnClickListener(this);
        tv_title_name.setText("登录");
        tv_register.setOnClickListener(this);
        tv_forget_pass.setOnClickListener(this);
        tv_login.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.tv_login:
                openActivity(MainActivity.class);
                break;
            case R.id.tv_register:

//                openActivity(RegisterActivity.class);
                break;
            case R.id.tv_forget_pass:
//                openActivity(ForgetPasswordActivity.class);
                break;

        }

    }
}
