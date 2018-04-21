package com.forest.market.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.forest.market.R;
import com.forest.market.adapter.base.RecyclerBaseAdapter;
import com.forest.market.adapter.commonweal.CommonwealAdapter;
import com.forest.market.adapter.commonweal.RankAdapter;
import com.forest.market.fragment.base.BaseFragment;
import com.forest.market.util.ToastUtil;
import com.forest.market.widget.RefreshRecyclerView;

import butterknife.BindView;

/**
 * 森林公益
 */
public class CommonwealFragment extends BaseFragment implements View.OnClickListener, RefreshRecyclerView.OnRefreshListener, RecyclerBaseAdapter.OnItemLongClickListener {
    @BindView(R.id.tv_title_name)
    TextView tv_title_name;
    @BindView(R.id.iv_add)
    ImageView iv_add;
    @BindView(R.id.recycler)
    RefreshRecyclerView recycler;
    RankAdapter adapter;


    @Override
    protected void initData() {
        tv_title_name.setText("公益");
        iv_add.setOnClickListener(this);
        adapter=new RankAdapter(context);
        //配置当前的主recycler
        recycler.setLayoutManager(new GridLayoutManager(context, 1));
//        recycler.setOnLongClickListener(this);
       adapter.setOnItemLongClickListener(this);
        recycler.addOnRefreshListener(this);
        recycler.setRefreshMode(0);
        recycler.setAdapter(adapter);

    }

    public static CommonwealFragment newInstance() {
        CommonwealFragment fragment = new CommonwealFragment();
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_commonweal;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_add:
                ToastUtil.showToast("添加公益");
                break;
        }

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onItemLongClick(int pos) {
        ToastUtil.showToast("長按");
    }

}
