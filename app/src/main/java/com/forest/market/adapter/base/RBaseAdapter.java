package com.forest.market.adapter.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guoqingwen on 2016/07/28 14:13
 */
public abstract class RBaseAdapter<T, VH extends RBaseAdapter.ViewHolder> extends BaseAdapter {

    protected List<T> list;
    protected LayoutInflater inflater;
    protected Context context;
    protected int selectIndex = -1;

    public RBaseAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = new ArrayList<T>();
    }

    public int getSelectIndex() {
        return selectIndex;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        if (list == null) {
            return;
        }
        this.list = list;
        notifyDataSetChanged();
    }

    public void addItem(T t) {
        if (t == null) {
            return;
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(t);
        notifyDataSetChanged();
    }

    public void addBottom(T t, boolean isClearOld) {
        if (isClearOld) {
            list.clear();
        }
        if (t != null) {
            list.add(t);
        }
        notifyDataSetChanged();
    }

    public void addBottom(List<T> data, boolean isClearOld) {
        if (isClearOld) {
            list.clear();
        }
        if (data != null) {
            list.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void addTop(T t, boolean isClearOld) {
        if (isClearOld) {
            list.clear();
        }
        if (t != null) {
            list.add(0, t);
        }
        notifyDataSetChanged();
    }

    public void addTop(List<T> data, boolean isClearOld) {
        if (isClearOld) {
            list.clear();
        }
        if (data != null) {
            list.addAll(0, data);
        }
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if (list != null && list.size() > position) {
            list.remove(position);
            notifyDataSetChanged();
        }
    }

    public int getCount() {
        return list == null ? 0 : list.size();
    }

    public T getItem(int position) {
        return list.get(position);
    }

    public T getSelect() {
        if (selectIndex == -1) {
            return null;
        }
        return list.get(selectIndex);
    }

    public void setSelect(int position) {
        if (position == selectIndex) {
            return;
        }
        selectIndex = position;
        notifyDataSetChanged();
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH vh;
        if (convertView == null) {
            convertView = inflater.inflate(getLayout(), parent, false);
            AutoUtils.autoSize(convertView);
            vh = getViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (VH) convertView.getTag();
        }
        if (position < list.size()) {
            onBindViewHolder(vh, list.get(position), position,parent);
        }
        if (position == selectIndex) {
            convertView.setSelected(true);
        } else {
            convertView.setSelected(false);
        }
        return convertView;
    }

    public abstract void onBindViewHolder(VH holder, T item, int position,View parent);
    public abstract int getLayout();

    public abstract VH getViewHolder(View view);

    public static class ViewHolder {

        public ViewHolder(View view) {
        }
    }
}