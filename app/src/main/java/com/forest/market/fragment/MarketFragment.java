package com.forest.market.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.forest.market.R;
import com.forest.market.adapter.commonweal.CommonwealAdapter;
import com.forest.market.adapter.market.MarketAdapter;
import com.forest.market.fragment.base.BaseFragment;
import com.forest.market.widget.RefreshRecyclerView;

import butterknife.BindView;


public class MarketFragment extends BaseFragment implements View.OnClickListener, RefreshRecyclerView.OnRefreshListener {
    @BindView(R.id.tv_title_name)
    TextView tv_title_name;
    @BindView(R.id.recycler)
    RefreshRecyclerView recycler;
    MarketAdapter adapter;

    @Override
    protected void initData() {
        tv_title_name.setText("我的市场");
        adapter=new MarketAdapter(context);
        //配置当前的主recycler
        recycler.setLayoutManager(new GridLayoutManager(context, 1));

        recycler.addOnRefreshListener(this);
        recycler.setRefreshMode(0);
        recycler.setAdapter(adapter);
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

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
