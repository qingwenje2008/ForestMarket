package com.forest.market.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 作者：任少东
 * 日期：2016/8/9 17:52
 */
public class NoScrollerListView extends ListView {
    public NoScrollerListView(Context context) {
        super(context);
    }

    public NoScrollerListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollerListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
