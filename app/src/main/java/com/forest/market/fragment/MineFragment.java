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
    @BindView(R.id.iv_mine_edit_myinfo)
    ImageView iv_mine_edit_myinfo;
    @BindView(R.id.iv_mine_message)
    ImageView iv_mine_message;

    @BindView(R.id.tv_mine_rank)
    TextView tv_mine_rank;
    @BindView(R.id.lin_mine_attention)
    LinearLayout lin_mine_attention;
    @BindView(R.id.lin_mine_fans)
    LinearLayout lin_mine_fans;
    @BindView(R.id.tv_mine_order)
    TextView tv_mine_order;
    @BindView(R.id.tv_collection)
    TextView tv_collection;
    @BindView(R.id.tv_jifen_task)
    TextView tv_jifen_task;
    @BindView(R.id.tv_jifen_mall)
    TextView tv_jifen_mall;
    @BindView(R.id.tv_mine_quanzi)
    TextView tv_mine_quanzi;
    @BindView(R.id.tv_mine_authentication)
    TextView tv_mine_authentication;
    @BindView(R.id.tv_site)
    TextView tv_site;
    @BindView(R.id.tv_setting)
    TextView tv_setting;
    @BindView(R.id.tv_service)
    TextView tv_service;
    @BindView(R.id.tv_mine_fans_contribution)
    TextView tv_mine_fans_contribution;
    @BindView(R.id.iv_mine_icon)
    ImageView iv_mine_icon;

    @Override
    protected void initData() {
        iv_mine_edit_myinfo.setOnClickListener(this);
        iv_mine_message.setOnClickListener(this);
        lin_mine_fans.setOnClickListener(this);
        lin_mine_attention.setOnClickListener(this);
        tv_mine_order.setOnClickListener(this);
        tv_collection.setOnClickListener(this);
        tv_jifen_task.setOnClickListener(this);
        tv_jifen_mall.setOnClickListener(this);
        tv_mine_quanzi.setOnClickListener(this);
        tv_mine_authentication.setOnClickListener(this);
        tv_mine_rank.setOnClickListener(this);
        tv_site.setOnClickListener(this);
        tv_setting.setOnClickListener(this);
        tv_service.setOnClickListener(this);
        tv_mine_fans_contribution.setOnClickListener(this);
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
            case R.id.iv_mine_edit_myinfo:

                break;
            case R.id.iv_mine_message:
                break;
            case R.id.lin_mine_attention:
                Bundle bundle=new Bundle();
                bundle.putInt("from",0);
//                openActivity(MyAttentionActivity.class,bundle);
                break;
            case R.id.lin_mine_fans:
                Bundle bundle1=new Bundle();
                bundle1.putInt("from",1);
//                openActivity(MyAttentionActivity.class,bundle1);
                break;
            case R.id.tv_mine_order:
//                openActivity(OrderActivity.class,null);
                break;

            case R.id.tv_collection:
//                openActivity(MyCollectionActivity.class,null);
                break;
            case R.id.tv_jifen_task:
//                openActivity(JiFenTaskActivity.class,null);
                break;
            case R.id.tv_jifen_mall:
//                openActivity(JiFenMallActivity.class,null);
                break;
            case R.id.tv_mine_quanzi:
//                openActivity(MyQuanZiActivity.class,null);
                break;
            case R.id.tv_mine_authentication:
//                openActivity(AuthenticationActivity.class,null);
                break;
            case R.id.tv_mine_rank:
//                openActivity(BigGodRankActivity.class,null);
                break;
            case R.id.tv_site:
//                openActivity(AddressManageActivity.class,null);
                break;
            case R.id.tv_setting:
//                openActivity(SettingActivity.class,null);
                break;
            case R.id.tv_service:
//                openActivity(ServiceOnlineActivity.class,null);
                break;
            case R.id.tv_mine_fans_contribution:
//                openActivity(MyWalletActivity.class);
                break;
            case R.id.iv_mine_icon:
//                openActivity(LoginActivity.class);
                break;
        }

    }
}
