package com.forest.market.util;

/**
 * Created by qingwenguo on 2018/4/8.
 */

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.forest.market.R;
import com.zhy.autolayout.utils.ScreenUtils;

/**
 * Created by imac on 15/7/8.
 */
public class PopOptionUtil {

    private Context mContext;
    private int popupWidth;
    private int popupHeight;
    private PopupWindow popupWindow;
    private PopClickEvent mEvent;
    private Button preBt;
    private Button nextBt;
    View popupView;

    public PopOptionUtil(Context context) {
        mContext = context;
        popupView= LayoutInflater.from(mContext).inflate(R.layout.pop_edit, null);
        preBt = (Button) popupView.findViewById(R.id.bt_l);
        nextBt = (Button) popupView.findViewById(R.id.bt_r);
        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        popupWidth = popupView.getMeasuredWidth();
        popupHeight = popupView.getMeasuredHeight();

//        View contentView = getPopupWindowContentView();
        popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);


    }

    public void show(View view) {
        initEvent();
        LogUtil.i(popupView+"");
        int windowPos[] = PopupWindowUtil.calculatePopWindowPos(view, popupView);
        int xOff =20; // 可以自己调整偏移
        windowPos[0] -= xOff;
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        popupWindow.showAtLocation(view, Gravity.TOP | Gravity.START, (location[0] + view.getWidth() / 2) - popupWidth / 2, windowPos[1]);


//        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, (location[0] + view.getWidth() / 2) - popupWidth / 2,
//                location[1] - popupHeight);
    }

    public void setOnPopClickEvent(PopClickEvent mEvent) {
        this.mEvent = mEvent;
    }

    private void initEvent() {
        if (mEvent != null) {
            preBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mEvent.onPreClick();
                }
            });
            nextBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mEvent.onNextClick();
                }
            });

        }
    }
    public interface PopClickEvent {
        public void onPreClick();
        public void onNextClick();
    }
}