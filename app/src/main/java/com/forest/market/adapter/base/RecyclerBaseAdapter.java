package com.forest.market.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guoqingwen on 2016/08/10 16:41
 */
public abstract class RecyclerBaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements View.OnClickListener {
    protected Context context;
    protected List<T> list;
    protected LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public RecyclerBaseAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        list = new ArrayList<>();

    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addBottom(List<T> list, boolean isClean){
        if (isClean){
            this.list=list;
        } else {
            if (this.list!=null){
                this.list.addAll(list);
            }
        }
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onViewDetachedFromWindow(VH holder) {
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
        if (position < list.size()) {
            onBindViewHolder(holder, list.get(position), position);
        } else {
            onBindViewHolder(holder,null,position);
        }
    }

    public abstract void onBindViewHolder(VH holder, T item, int position);

    @Override
    public int getItemCount() {
        return getMyItemCount();
//        if (list==null) {
//            getMyItemCount();
//        }
//        return list == null ? 0 : list.size();
    }

    public int getMyItemCount() {

        return list.size()==0?10:list.size();
    }

    public T getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public void onClick(View v) {
        int pos = (int) v.getTag();
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(pos);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int pos);
    }
}
