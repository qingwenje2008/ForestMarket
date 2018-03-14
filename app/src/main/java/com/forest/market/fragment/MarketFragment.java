package com.forest.market.fragment;

import android.view.View;
import android.widget.TextView;

import com.forest.market.R;
import com.forest.market.fragment.base.BaseFragment;

import butterknife.BindView;


public class MarketFragment extends BaseFragment implements View.OnClickListener{
    @BindView(R.id.tv_title_name)
    TextView tv_title_name;


    @Override
    protected void initData() {
        tv_title_name.setText("我的市场");
    }

    public static MarketFragment newInstance() {
        MarketFragment fragment = new MarketFragment();
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_market;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }

    }
}
