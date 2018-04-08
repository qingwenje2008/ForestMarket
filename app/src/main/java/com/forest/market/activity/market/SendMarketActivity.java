package com.forest.market.activity.market;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.forest.market.R;
import com.forest.market.activity.base.BaseActivity;
import com.forest.market.activity.base.GetPhotoActivity;
import com.forest.market.adapter.market.PicAdapter;
import com.forest.market.dialog.RAlertDialog;
import com.forest.market.util.ToastUtil;
import com.forest.market.widget.NoScrollerGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

public class SendMarketActivity extends GetPhotoActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    @BindView(R.id.view)
    View view;
    @BindView(R.id.iv_title_back)
    ImageView iv_title_back;
    @BindView(R.id.tv_title_name)
    TextView tv_title_name;
    @BindView(R.id.tv_title_right_text)
    TextView tv_title_right_text;
    @BindView(R.id.et_content)
    EditText et_content;
    @BindView(R.id.gv_pic)
    NoScrollerGridView gv_pic;
    private PicAdapter picAdapter;
    private List<String> images=new ArrayList<>();
    private RAlertDialog bottomMenuDialog;
    @Override
    protected void initData() {
        iv_title_back.setVisibility(View.VISIBLE);
        iv_title_back.setImageResource(R.mipmap.nav_back_h);
        iv_title_back.setOnClickListener(this);
        tv_title_right_text.setVisibility(View.VISIBLE);
        tv_title_right_text.setText("发布");
        tv_title_right_text.setOnClickListener(this);
        tv_title_name.setText("发贴");
        picAdapter = new PicAdapter(this, images);
        gv_pic.setAdapter(picAdapter);
        gv_pic.setOnItemClickListener(this);
        gv_pic.setOnItemLongClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sendmarket;
    }
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
            case R.id.tv_title_right_text:
                finish();
                ToastUtil.showToast("发布成功");
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == images.size()) {
            selectPhoto();

        } else {
            //
             bottomMenuDialog.show();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }
    public void ShowDialog(final int position) {
        if (bottomMenuDialog==null){

            bottomMenuDialog = new RAlertDialog(this);
            bottomMenuDialog.setConfirmText("删除");
            bottomMenuDialog.setMsg("是否删除此图片");
            bottomMenuDialog.setOnConfirmClickListener(new RAlertDialog.OnConfirmClickListener() {
                @Override
                public void onConfirmClick(int type) {
                    images.remove(position);
                    picAdapter.notifyDataSetChanged();
                    bottomMenuDialog.dismiss();
                }
            });
        }else {
            bottomMenuDialog.show();
        }

    }
}
