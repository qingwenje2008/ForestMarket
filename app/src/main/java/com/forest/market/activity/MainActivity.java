package com.forest.market.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.forest.market.R;
import com.forest.market.activity.base.BaseActivity;
import com.forest.market.fragment.CommonwealFragment;
import com.forest.market.fragment.MarketFragment;
import com.forest.market.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.view)
    View view;
    @BindView(R.id.iv_main_home)
    ImageView iv_main_home;
    @BindView(R.id.iv_main_referee)
    ImageView iv_main_referee;
    @BindView(R.id.iv_main_my)
    ImageView iv_main_my;
    @BindView(R.id.tv_main_home)
    TextView tv_main_home;
    @BindView(R.id.tv_main_referee)
    TextView tv_main_referee;
    @BindView(R.id.tv_main_my)
    TextView tv_main_my;
    @BindView(R.id.main_home_layout)
    LinearLayout main_home_layout;
    @BindView(R.id.main_referee)
    LinearLayout main_referee;
    @BindView(R.id.main_my_layout)
    LinearLayout main_my_layout;
    @BindView(R.id.main_framelayout)
    FrameLayout mainFramelayout;

    private int lastIndex = -1;
    private FragmentManager supportFragmentManager;
    private FragmentTransaction fragmentTransaction;
    private View[] mLayouts;
    private ImageView[] mImageViews;
    private TextView[] mTextViews;
    private List<Fragment> fragmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        supportFragmentManager = getSupportFragmentManager();

        fragmentList = new ArrayList<>();
        fragmentList.add(MarketFragment.newInstance());
        fragmentList.add(CommonwealFragment.newInstance());
        fragmentList.add(MineFragment.newInstance());

        mImageViews = new ImageView[3];
        mImageViews[0] = iv_main_home;
        mImageViews[1] = iv_main_referee;
        mImageViews[2] = iv_main_my;
        mTextViews = new TextView[3];
        mTextViews[0] = tv_main_home;
        mTextViews[1] = tv_main_referee;
        mTextViews[2] = tv_main_my;

        mLayouts = new View[3];
        mLayouts[0] = main_home_layout;
        mLayouts[1] = main_referee;
        mLayouts[2] = main_my_layout;

        for (int i = 0;i<mLayouts.length;i++){

            mLayouts[i].setTag(i);
            mLayouts[i].setOnClickListener(this);
        }
        setCurrent(0);

    }
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(view).init();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        setCurrent(tag);
    }

    private void setCurrent(int index) {
        fragmentTransaction = supportFragmentManager.beginTransaction();
        if (index == lastIndex)return;

        if (!fragmentList.get(index).isAdded()){
            fragmentTransaction.add(mainFramelayout.getId(),fragmentList.get(index));
        }

        fragmentTransaction.show(fragmentList.get(index));
        if (lastIndex != -1){
            fragmentTransaction.hide(fragmentList.get(lastIndex));
        }
        fragmentTransaction.commit();

        mImageViews[index].setSelected(true);
        mTextViews[index].setTextColor(Color.parseColor("#3ebcd3"));
        if (lastIndex != -1){
            mImageViews[lastIndex].setSelected(false);
            mTextViews[lastIndex].setTextColor(Color.parseColor("#b1ced3"));
        }

        lastIndex = index;
    }


    public void setShengHua(int i) {
        setCurrent(i);
        mImageViews[i].setSelected(true);
    }
    private long firstTime;
    @Override
    public void onBackPressed() {
        if (firstTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT).show();

        }
        firstTime = System.currentTimeMillis();
    }
}
