package com.forest.market.adapter.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 任少东 on 2017/01/05 16:29
 */
public abstract class ListBaseAdapter<T> extends BaseAdapter {

    protected List<T> list;
    protected LayoutInflater inflater;
    protected Context context;

    public ListBaseAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = new ArrayList<T>();
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.list = list;
        notifyDataSetChanged();
    }
    public int getCount() {
        return list == null ? 3 : list.size();
    }

    public T getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }
}
