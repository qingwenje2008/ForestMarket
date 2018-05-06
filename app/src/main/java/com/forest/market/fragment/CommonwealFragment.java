package com.forest.market.fragment;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.forest.market.R;
import com.forest.market.adapter.base.RecyclerBaseAdapter;
import com.forest.market.adapter.commonweal.CommonwealAdapter;
import com.forest.market.adapter.commonweal.MRankAdapter;
import com.forest.market.adapter.commonweal.RankAdapter;
import com.forest.market.fragment.base.BaseFragment;
import com.forest.market.util.ToastUtil;
import com.forest.market.util.LogUtil;
import com.forest.market.util.PopOptionUtil;
import com.forest.market.widget.NoScrollerListView;
import com.forest.market.widget.RefreshRecyclerView;

import butterknife.BindView;

/**
 * 森林公益
 */
public class CommonwealFragment extends BaseFragment implements View.OnClickListener, RefreshRecyclerView.OnRefreshListener, RecyclerBaseAdapter.OnItemLongClickListener, RecyclerBaseAdapter.OnItemClickListener {
    @BindView(R.id.tv_title_name)
    TextView tv_title_name;
    @BindView(R.id.iv_add)
    ImageView iv_add;
    @BindView(R.id.recycler)
    RefreshRecyclerView commonwealRecycler;
    CommonwealAdapter commonwealAdapter;
    PopOptionUtil mPop;
    int width;
    int height;
    @Override
    protected void initData() {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        width = metric.widthPixels;     // 屏幕宽度（像素）
        height = metric.heightPixels;   // 屏幕高度（像素）
        tv_title_name.setText("公益");
        iv_add.setOnClickListener(this);
        commonwealAdapter=new CommonwealAdapter(context);
        //配置当前的主recycler
        commonwealRecycler.setLayoutManager(new GridLayoutManager(context, 1));
//        recycler.setOnLongClickListener(this);
        commonwealAdapter.setOnItemLongClickListener(this);
        commonwealRecycler.addOnRefreshListener(this);
        commonwealRecycler.setRefreshMode(0);
        commonwealRecycler.setAdapter(commonwealAdapter);
        commonwealAdapter.setOnItemClickListener(this);
        mPop = new PopOptionUtil(context);
        mPop.setOnPopClickEvent(new PopOptionUtil.PopClickEvent() {
            @Override
            public void onPreClick() {
                Toast.makeText(context,"置顶",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNextClick() {
                Toast.makeText(context,"删除",Toast.LENGTH_SHORT).show();
            }
        });
        addHeader();
    }
    public void addHeader(){
        View view= LayoutInflater.from(context).inflate(R.layout.header_rank,null);
        NoScrollerListView recycler = (NoScrollerListView) view.findViewById(R.id.rankRecycler);
        MRankAdapter adapter=new MRankAdapter(context);
        //配置当前的主recycler
//        recycler.setLayoutManager(new GridLayoutManager(context, 1));
//        adapter.setOnItemLongClickListener(this);
        recycler.setAdapter(adapter);

        commonwealRecycler.addHeaderView(view);
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
    public void onItemLongClick(int pos,View v) {
        ToastUtil.showToast("長按");
        int []outLocation=new int[2];
        v.getLocationOnScreen(outLocation);
        v.measure(0,0);
        int h=v.getMeasuredHeight();
        LogUtil.i("组件高:"+h);
        LogUtil.i("屏幕高："+height+"，组件到底部的距离"+(height- outLocation[1]));

        mPop.show(v);
    }

    public void onItemClick(int pos,View v) {

    }
}
