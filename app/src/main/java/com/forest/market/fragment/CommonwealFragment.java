package com.forest.market.fragment;

import android.view.View;
import android.widget.TextView;

import com.forest.market.R;
import com.forest.market.fragment.base.BaseFragment;

import butterknife.BindView;

/**
 * 森林公益
 */
public class CommonwealFragment extends BaseFragment implements View.OnClickListener{
    @BindView(R.id.tv_title_name)
    TextView tv_title_name;


    @Override
    protected void initData() {
        tv_title_name.setText("公益");
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
}
