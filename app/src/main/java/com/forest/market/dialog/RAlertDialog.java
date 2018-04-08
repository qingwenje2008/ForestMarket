package com.forest.market.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.forest.market.R;


/**
 * Created by 任少东 on 2016/10/12 14:47
 */
public class RAlertDialog extends RCenterDialog implements View.OnClickListener {

    private TextView title, message, cancel, confirm;
    private OnConfirmClickListener onConfirmClickListener;
    private int type;

    public RAlertDialog(Context context) {
        super(context);
    }

    @Override
    protected View getContentView() {
        View view = getLayoutInflater().inflate(R.layout.dialog_alert, null);
        title = (TextView) view.findViewById(R.id.title);
        message = (TextView) view.findViewById(R.id.message);
        cancel = (TextView) view.findViewById(R.id.cancel);
        confirm = (TextView) view.findViewById(R.id.confirm);
        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);
        return view;
    }

    public RAlertDialog setTitle(String tit) {
        if (tit == null) {
            title.setVisibility(View.GONE);
        } else {
            title.setVisibility(View.VISIBLE);
            title.setText(tit);
        }
        return this;
    }

    public RAlertDialog setMsg(String msg) {
        message.setText(msg);
        return this;
    }

    public RAlertDialog setCancelText(String str) {
        if (TextUtils.isEmpty(str)) {
            cancel.setVisibility(View.GONE);
            return this;
        }
        cancel.setText(str);
        return this;
    }

    public RAlertDialog setConfirmText(String str) {
        confirm.setText(str);
        return this;
    }

    public void show(int type) {
        this.type = type;
        super.show();
    }

    @Override
    protected float getWidthScale() {
        return 0.72f;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm:
                if (onConfirmClickListener != null) {
                    onConfirmClickListener.onConfirmClick(type);
                }
                break;
        }
        dismiss();
    }

    public void setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener) {
        this.onConfirmClickListener = onConfirmClickListener;
    }

    public interface OnConfirmClickListener {
        void onConfirmClick(int type);
    }
}
