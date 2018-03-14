package com.guo.market.util;

import android.view.Gravity;
import android.widget.Toast;

import com.guo.market.App;

/**
 * Created by guoqingwen on 2016/07/28 14:05
 */
public class ToastUtil {
    private static Toast toast;
    private static Toast centerToast;
    private static Toast centerToast2;

    public static void showToast(String msg) {
        try {
            if (toast == null) {
                toast = Toast.makeText(App.getContext(), msg, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
            }
            toast.setText(msg);
            toast.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
//
//    public static void showCenterToast(String msg, int res) {
//        showCenterToast(App.getContext(), res, msg);
//    }
//
//
//    public static void showCenterToast(String text) {
//        showCenterToast(App.getContext(), R.mipmap.ic_launcher, text);
//    }
//
//    public static void showCenterToast(String title,String msg){
//        if (centerToast2==null){
//            View view= LayoutInflater.from(App.getContext()).inflate(R.layout.toast_center,null);
//            centerToast2 = new Toast(App.getContext());
//            centerToast2.setView(view);
//            centerToast2.setGravity(Gravity.CENTER, 0, 0);
//        } else {
//            View view=centerToast2.getView();
//            TextView message= (TextView) view.findViewById(R.id.text);
//            message.setText(msg);
//        }
//        centerToast2.show();
//    }
//
//    public static void showCenterToast(Context context, int imgRes, String text) {
//        if (centerToast == null) {
//            LinearLayout toastLay = new LinearLayout(context);
//
//            toastLay.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
//
//            toastLay.setOrientation(LinearLayout.VERTICAL);
//            toastLay.setGravity(Gravity.CENTER);
//            int dp = ScreenUtil.dp2px(1);
//            toastLay.setPadding(dp * 20, 10 * dp, 20 * dp, 10 * dp);
//            GradientDrawable drawable = new GradientDrawable();
//            drawable.setCornerRadius(5 * dp);
//            drawable.setColor(0x88000000);
//            toastLay.setBackgroundDrawable(drawable);
//            ImageView image = new ImageView(context);
//            image.setPadding(10 * dp, 10 * dp, 10 * dp, 10 * dp);
//            image.setImageResource(imgRes);
//            toastLay.addView(image);
//            TextView textView = new TextView(context);
//            textView.setTextColor(0xffffffff);
//            textView.setTextSize(16);
//            textView.setText(text);
//            toastLay.addView(textView);
//            centerToast = new Toast(context);
//            centerToast.setView(toastLay);
//
//            centerToast.setGravity(Gravity.CENTER, 0, 0);
//        } else {
//            LinearLayout layout = (LinearLayout) centerToast.getView();
//            ImageView image = (ImageView) layout.getChildAt(0);
//            TextView textView = (TextView) layout.getChildAt(1);
//            image.setImageResource(imgRes);
//            textView.setText(text);
//        }
//        centerToast.show();
//    }
}
