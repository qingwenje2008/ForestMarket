package com.forest.market.adapter.market;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.forest.market.R;
import com.forest.market.adapter.base.ListBaseAdapter;
import com.forest.market.adapter.base.RBaseAdapter;
import com.forest.market.entity.Comment;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by qingwenguo on 2018/3/25.
 */

public class ListCommentAdatper extends ListBaseAdapter<Comment>{
    public ListCommentAdatper(Context context) {
        super(context);
        initList();
    }
    public void initList(){
        list.add(new Comment("小二","为什么"));
        list.add(new Comment("老师","不为什么"));
        list.add(new Comment("小二","为什么不为什么"));


    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Comment comment =list.get(i);
        ViewHolder vh;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_comment, null);
            AutoUtils.auto(view);
            vh = new ViewHolder(view);
            view.setTag(vh);
        } else {
            vh= (ViewHolder) view.getTag();
        }
        vh.name.setText(comment.getNickName());
        vh.content.setText(comment.getContent());
        return view;
    }
    protected class ViewHolder {
        private TextView name;
        private TextView content;

        public ViewHolder(View v){
            name = (TextView) v.findViewById(R.id.nickname);
            content = (TextView) v.findViewById(R.id.content);

        }

    }
}
