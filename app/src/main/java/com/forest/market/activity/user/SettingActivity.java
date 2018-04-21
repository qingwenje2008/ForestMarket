package com.forest.market.activity.user;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.forest.market.R;
import com.forest.market.activity.base.GetPhotoActivity;
import com.forest.market.dialog.RAlertDialog;

import butterknife.BindView;

/**
 * Created by qingwenguo on 2018/3/18.
 */

public class SettingActivity extends GetPhotoActivity implements View.OnClickListener, RAlertDialog.OnConfirmClickListener {
    @BindView(R.id.view)
    View view;
    @BindView(R.id.iv_title_back)
    ImageView iv_title_back;
    @BindView(R.id.tv_title_name)
    TextView tv_title_name;
    @BindView(R.id.lin_update_password)
    LinearLayout lin_update_password;
    @BindView(R.id.lin_about)
    LinearLayout lin_about;
    @BindView(R.id.lin_exit)
    LinearLayout lin_exit;
    private RAlertDialog closeDialog;
    @Override
    protected void initData() {
        iv_title_back.setVisibility(View.VISIBLE);
        iv_title_back.setImageResource(R.mipmap.nav_back_h);
        iv_title_back.setOnClickListener(this);
        tv_title_name.setText("设置");
        lin_update_password.setOnClickListener(this);
        lin_about.setOnClickListener(this);
        lin_exit.setOnClickListener(this);

        closeDialog = new RAlertDialog(this);
        closeDialog.setMsg("是否退出登录");
        closeDialog.setOnConfirmClickListener(this);
    }
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(view).init();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.lin_update_password:
                openActivity(UpdatePasswordActivity.class);
                break;
            case R.id.lin_about:
                openActivity(AboutUsActivity.class,null);
                break;
            case R.id.lin_exit:
//                openActivity(UpdateNameActivity.class,null);
                break;



        }
    }
    @Override
    protected void onCrop(String path) {
        super.onCrop(path);
//        uploadPicHelper.upload(path, Constants.AVATAR);
    }

    @Override
    public void onConfirmClick(int type) {
        finish();
    }
}
