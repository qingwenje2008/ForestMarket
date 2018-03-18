package com.forest.market.activity.user;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.forest.market.R;
import com.forest.market.activity.base.BaseActivity;
import com.forest.market.util.ToastUtil;

import butterknife.BindView;

/**
 * Created by 郭庆文 on 17/2/28 上午10:02
 */

public class UpdateNameActivity extends BaseActivity implements View.OnClickListener
{
    @BindView(R.id.view)
    View view;
    @BindView(R.id.iv_title_back)
    ImageView iv_title_back;
    @BindView(R.id.tv_title_name)
    TextView tv_title_name;
    @BindView(R.id.tv_ok)
    TextView tv_ok;
    @BindView(R.id.et_name)
    EditText et_name;

    @Override
    protected void initData() {
        iv_title_back.setVisibility(View.VISIBLE);
        iv_title_back.setImageResource(R.mipmap.nav_back_h);
        iv_title_back.setOnClickListener(this);
        tv_title_name.setText("修改信息");
        tv_ok.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_name;
    }
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(view).init();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.tv_ok:
                ToastUtil.showToast("修改成功");
                finish();
                break;

        }

    }
}
