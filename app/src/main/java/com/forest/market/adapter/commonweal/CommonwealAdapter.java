package com.forest.market.adapter.commonweal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.forest.market.R;
import com.forest.market.adapter.base.RBaseAdapter;
import com.forest.market.adapter.base.RecyclerBaseAdapter;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by qingwenguo on 2018/3/23.
 */

public class CommonwealAdapter extends RecyclerBaseAdapter<Object,CommonwealAdapter.ViewHolder> {
    public CommonwealAdapter(Context context) {
        super(context);
    }
//    @Override
//    public int getItemViewType(int position) {
////        if (position>2){
////            return TYPE_SMALL;
////        }
////        return TYPE_BIG;
//        return position;
//    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_commonweal, null);
        AutoUtils.auto(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, Object item, int position) {

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private Button tv_btn;
        public ViewHolder(View itemView) {

            super(itemView);
            tv_btn = (Button) itemView.findViewById(R.id.tv_btn);
//            iv_num = (ImageView) itemView.findViewById(R.id.iv_num);
//            tv_nickname = (TextView) itemView.findViewById(R.id.tv_nickname);
//            tv_type_god = (TextView) itemView.findViewById(R.id.tv_type_god);
//            line_fenge = (TextView) itemView.findViewById(R.id.line_fenge);
        }

    }
}
