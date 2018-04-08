package com.forest.market.adapter.commonweal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.forest.market.R;
import com.forest.market.adapter.base.RecyclerBaseAdapter;


public class RankAdapter extends RecyclerBaseAdapter<Object, RecyclerView.ViewHolder> {

    private int TYPE_BIG = 99;
    private int TYPE_SMALL = 101;
    private RecyclerView.ViewHolder holder;
    private Object item;
    private int position;

    @Override
    public int getItemViewType(int position) {
        if (position < 3) return TYPE_BIG;
        return TYPE_SMALL;
    }

    public RankAdapter(Context context) {
        super(context);
    }

    View view = null;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("guoguo", viewType + "-----viewType-");

        if (viewType == TYPE_BIG) {
            view = inflater.inflate(R.layout.item_rank_big, null);
            return new ViewHolderBig(view);
        } else {
            view = inflater.inflate(R.layout.item_rank_small, null);
            return new ViewHolderSmall(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, Object item, int position) {
        if (holder instanceof ViewHolderBig) {
            ViewHolderBig viewHolderBig= (ViewHolderBig) holder;

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

        } else if (holder instanceof ViewHolderSmall) {
            ViewHolderSmall viewHolderSmall= (ViewHolderSmall) holder;
            viewHolderSmall.tv_fans_contribution_num.setText(position+1+"");


        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    protected class ViewHolderBig extends RecyclerView.ViewHolder {
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

    protected class ViewHolderSmall extends RecyclerView.ViewHolder {
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
