package com.forest.market.adapter.market;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.forest.market.R;
import com.forest.market.adapter.base.RecyclerBaseAdapter;
import com.forest.market.fragment.MarketFragment;
import com.forest.market.widget.NoScrollerGridView;
import com.forest.market.widget.NoScrollerListView;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;

/**
 * Created by qingwenguo on 2018/3/23.
 */

public class MarketAdapter extends RecyclerBaseAdapter<Object,MarketAdapter.ViewHolder> {
    public MarketAdapter(Context context) {
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
        View view = inflater.inflate(R.layout.item_market, null);
        AutoUtils.auto(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, Object item, int position) {
        GridImageAdapter gridImageAdapter=new GridImageAdapter(context);
        ListCommentAdatper listCommentAdatper=new ListCommentAdatper(context);
        ArrayList list=new ArrayList();
        list.add(1);
        list.add(1);
        list.add(1);
        gridImageAdapter.setList(list);
        holder.grid_img.setAdapter(gridImageAdapter);
        holder.list_comment.setAdapter(listCommentAdatper);
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private NoScrollerGridView grid_img;
        private NoScrollerListView list_comment;
        public ViewHolder(View itemView) {

            super(itemView);
            grid_img = (NoScrollerGridView) itemView.findViewById(R.id.grid_img);
            list_comment = (NoScrollerListView) itemView.findViewById(R.id.list_comment);
//            tv_nickname = (TextView) itemView.findViewById(R.id.tv_nickname);
//            tv_type_god = (TextView) itemView.findViewById(R.id.tv_type_god);
//            line_fenge = (TextView) itemView.findViewById(R.id.line_fenge);
        }

    }
}
