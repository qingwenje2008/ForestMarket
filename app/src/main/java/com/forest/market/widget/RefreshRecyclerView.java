package com.forest.market.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by guo on 2016/12/15 13:55
 */
public class RefreshRecyclerView extends XRecyclerView {
    private OnRefreshListener onRefreshListener;
    public RefreshRecyclerView(Context context) {
        super(context);
        init();
    }

    public RefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RefreshRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
//        setArrowImageView(R.mipmap.refresh_arrow);
        setRefreshProgressStyle(22);
        setLoadingMoreProgressStyle(22);
        setLoadingListener(new LoadingListener() {
            @Override
            public void onRefresh() {
                if (onRefreshListener != null) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onRefreshListener.onRefresh();
                            complete();
                        }
                    },1000);
                }
            }

            @Override
            public void onLoadMore() {
                if (onRefreshListener != null) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onRefreshListener.onLoadMore();
                            complete();
                        }
                    },1000);
                }
            }
        });
    }

    public void setRefreshMode(int mode){
        switch (mode){
            case 0:
                setPullRefreshEnabled(false);
                setLoadingMoreEnabled(false);
                break;
            case 1:
                setPullRefreshEnabled(true);
                setLoadingMoreEnabled(false);
                break;
            case 2:
                setPullRefreshEnabled(false);
                setLoadingMoreEnabled(true);
                break;
            case 3:
                setPullRefreshEnabled(true);
                setLoadingMoreEnabled(true);
                break;
        }
    }

    public void complete() {
        refreshComplete();
        loadMoreComplete();
    }

    public void addOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    public interface OnRefreshListener {
        void onRefresh();

        void onLoadMore();
    }
}
