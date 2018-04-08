package com.forest.market.fragment;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.forest.market.R;
import com.forest.market.adapter.base.RecyclerBaseAdapter;
import com.forest.market.adapter.commonweal.CommonwealAdapter;
import com.forest.market.adapter.commonweal.RankAdapter;
import com.forest.market.fragment.base.BaseFragment;
import com.forest.market.util.LogUtil;
import com.forest.market.util.PopOptionUtil;
import com.forest.market.widget.RefreshRecyclerView;

import butterknife.BindView;

/**
 * 森林公益
 */
public class CommonwealFragment extends BaseFragment implements View.OnClickListener, RefreshRecyclerView.OnRefreshListener, RecyclerBaseAdapter.OnItemClickListener {
    @BindView(R.id.tv_title_name)
    TextView tv_title_name;
    @BindView(R.id.recycler)
    RefreshRecyclerView recycler;
    RankAdapter adapter;

    PopOptionUtil mPop;
    int width;
    int height;
    @Override
    protected void initData() {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        width = metric.widthPixels;     // 屏幕宽度（像素）
        height = metric.heightPixels;   // 屏幕高度（像素）
//        float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
//        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）


        tv_title_name.setText("公益");
        adapter=new RankAdapter(context);
        //配置当前的主recycler
        recycler.setLayoutManager(new GridLayoutManager(context, 1));

        recycler.addOnRefreshListener(this);
        recycler.setRefreshMode(0);
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
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

        }

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onItemClick(int pos,View v) {
        int []outLocation=new int[2];
        v.getLocationOnScreen(outLocation);
        v.measure(0,0);
        int h=v.getMeasuredHeight();
        LogUtil.i("组件高:"+h);
//        LogUtil.i("到屏幕顶底部的距离是："+v.getBottom());
//        LogUtil.i("到屏幕顶底部的距离是："+v.getBottom());
//        LogUtil.i("到屏幕顶底部的距离是："+outLocation[1]);
        LogUtil.i("屏幕高："+height+"，组件到底部的距离"+(height- outLocation[1]));


//        recycler.getMeasuredState(0,0);

//        View view=.getParent();

        mPop.show(v);
    }
}
