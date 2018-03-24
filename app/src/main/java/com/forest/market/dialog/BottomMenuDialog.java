package com.forest.market.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.forest.market.R;
import com.forest.market.util.ScreenUtil;


/**
 * Created by 任少东 on 2016/07/29 15:43
 */
public class BottomMenuDialog extends RBottomDialog implements View.OnClickListener {
    private LinearLayout layout;
    private OnItemClickListener onItemClickListener;
    private int textSize = 15;
    private int textColor = 0xffffffff;

    public BottomMenuDialog(Context context) {
        super(context);
    }

    @Override
    protected View getContentView() {
        layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        // layout.setPadding(0, 40, 0, 40);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.setBackgroundColor(0xffeeeeee);
        return layout;
    }

    public void setMenus(String[] menus) {
        layout.removeAllViews();
        int height = ScreenUtil.dp2px(1);
        LinearLayout.LayoutParams menuParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height * 48);
        LinearLayout.LayoutParams dividerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
        for (int i = 0; i < menus.length + 1; i++) {
            TextView textView;
            if (i < menus.length) {
                textView = getTextView(menus[i], menuParams);
                textView.setTag(i);
            } else {
                textView = getTextView(context.getString(R.string.cancel), menuParams);
                textView.setTag(-1);
                View divider = new View(context);
                divider.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height * 8));
                layout.addView(divider);
            }
            textView.setOnClickListener(this);
            layout.addView(textView);

            View divider = new View(context);
            divider.setLayoutParams(dividerParams);
            divider.setBackgroundColor(0xffd1d1d1);
            if (i < menus.length) {
                layout.addView(divider);
            }
        }
    }

    private TextView getTextView(String string, LinearLayout.LayoutParams params) {
        TextView textView = new TextView(context);
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER);
        textView.setText(string);
        textView.setTextSize(textSize);
        textView.setBackgroundColor(0xffffffff);
        return textView;
    }

    @Override
    public void onClick(View v) {
        int pos = (int) v.getTag();
        if (onItemClickListener != null) {
            onItemClickListener.onClick(this, pos);
        }
        dismiss();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
