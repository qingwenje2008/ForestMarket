package com.forest.market.activity.user;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.forest.market.R;
import com.forest.market.activity.base.BaseActivity;
import com.forest.market.util.ToastUtil;

import butterknife.BindView;

public class UpdatePasswordActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_title_back)
    ImageView iv_title_back;
    @BindView(R.id.tv_title_name)
    TextView tv_title_name;
    @BindView(R.id.et_pass)
    EditText mOldPass;
    @BindView(R.id.et_newPwd)
    EditText mEtNewPwd;
    @BindView(R.id.et_againNewPwd)
    EditText mEtAgainNewPwd;
    @BindView(R.id.bt_commit)
    Button mBtCommit;


    @Override
    protected void initData() {
        mBtCommit.setOnClickListener(this);

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_password;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.bt_commit:
                modifyPassword();
                break;
        }
    }


    /**
     * 修改用户密码
     */
    private void modifyPassword() {
        String pwd = mOldPass.getText().toString().trim();
        String againNewPwd = mEtAgainNewPwd.getText().toString().trim();
        String passwordStr = mEtNewPwd.getText().toString().trim();
        if (verMsg(pwd, passwordStr)) return;
        if (TextUtils.isEmpty(againNewPwd)) {
            ToastUtil.showToast("重复密码不能为空");
            return;
        }
        if (!againNewPwd.equals(passwordStr)) {
            mEtNewPwd.setText("");
            mEtAgainNewPwd.setText("");
            ToastUtil.showToast("两次输入的密码不一致");
            return;
        }
        //修改密码
        ToastUtil.showToast("修改成功！");
        finish();
    }

    private boolean verMsg(String pwd, String passwordStr) {
        if (TextUtils.isEmpty(pwd)) {
            ToastUtil.showToast("请填写旧密码");
            return true;
        }
        if (TextUtils.isEmpty(passwordStr)) {
            ToastUtil.showToast("请填写新密码");
            return true;
        }
        if (pwd.length() < 6) {
            ToastUtil.showToast("旧密码不能少于6位");
            mOldPass.setText("");
            return true;
        }
        if (passwordStr.length() < 6) {
            ToastUtil.showToast("新密码不能少于6位");
            mEtNewPwd.setText("");
            mEtAgainNewPwd.setText("");
            return true;
        }
        return false;
    }


}
