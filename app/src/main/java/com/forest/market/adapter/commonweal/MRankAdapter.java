package com.forest.market.adapter.commonweal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.forest.market.R;
import com.forest.market.adapter.base.ListBaseAdapter;
import com.forest.market.adapter.base.RBaseAdapter;
import com.forest.market.util.LogUtil;

/**
 * Created by qingwenguo on 2018/5/6.
 */

public class MRankAdapter extends RBaseAdapter<Object,RBaseAdapter.ViewHolder> {
    private int TYPE_BIG = 99;
    private int TYPE_SMALL = 101;
    View view = null;
    public MRankAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position < 2) return TYPE_BIG;
        return TYPE_SMALL;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, Object item, int position, View parent) {
        if (holder instanceof MRankAdapter.ViewHolderBig) {
            MRankAdapter.ViewHolderBig viewHolderBig= (MRankAdapter.ViewHolderBig) holder;
            LogUtil.i(position+"");
            switch (position) {
                case 0:
                    viewHolderBig.iv_fans_contribution_num.setImageResource(R.mipmap.fsgxb_1);
                    break;
                case 1:
                    viewHolderBig.iv_fans_contribution_num.setImageResource(R.mipmap.fsgxb_2);
                    break;
                case 2:
                    viewHolderBig.iv_fans_contribution_num.setImageResource(R.mipmap.fsgxb_3);
                    break;
            }

        } else if (holder instanceof MRankAdapter.ViewHolderSmall) {
            MRankAdapter.ViewHolderSmall viewHolderSmall= (MRankAdapter.ViewHolderSmall) holder;
            viewHolderSmall.tv_fans_contribution_num.setText(position+1+"");


        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public int getLayout(int viewType) {
//        if (viewType == TYPE_BIG) {

            return R.layout.item_rank_big;
//        } else {
//            return R.layout.item_rank_small;
//        }
    }

    @Override
    public ViewHolder getViewHolder(View view,int viewType) {
//        if (viewType == TYPE_BIG) {
            return new MRankAdapter.ViewHolderBig(view);
//        } else {
//            return new MRankAdapter.ViewHolderSmall(view);
//        }

    }


    protected class ViewHolderBig extends ViewHolder {
        private TextView tv_fans_contribution_name;
        private TextView tv_fans_contribution_contri;
        private ImageView iv_fans_contribution_head;
        private ImageView iv_fans_contribution_num;

        public ViewHolderBig(View itemView) {
            super(itemView);
            iv_fans_contribution_num = (ImageView) itemView.findViewById(R.id.iv_fans_contribution_num);
            iv_fans_contribution_head = (ImageView) itemView.findViewById(R.id.iv_fans_contribution_head);
            tv_fans_contribution_name = (TextView) itemView.findViewById(R.id.tv_fans_contribution_name);
            tv_fans_contribution_contri = (TextView) itemView.findViewById(R.id.tv_fans_contribution_contri);
        }
    }

    protected class ViewHolderSmall extends ViewHolder {
        private TextView tv_fans_contribution_num;
        private TextView tv_fans_contribution_name;
        private TextView tv_fans_contribution_contri;
        private ImageView iv_fans_contribution_head;

        public ViewHolderSmall(View itemView) {

            super(itemView);
            tv_fans_contribution_num = (TextView) itemView.findViewById(R.id.tv_fans_contribution_num);
            iv_fans_contribution_head = (ImageView) itemView.findViewById(R.id.iv_fans_contribution_head);
            tv_fans_contribution_name = (TextView) itemView.findViewById(R.id.tv_fans_contribution_name);
            tv_fans_contribution_contri = (TextView) itemView.findViewById(R.id.tv_fans_contribution_contri);
        }

    }
}
