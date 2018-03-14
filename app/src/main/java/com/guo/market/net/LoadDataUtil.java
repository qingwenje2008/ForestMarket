package com.guo.market.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.guo.market.util.LogUtil;
import com.guo.market.util.MD5;
import com.guo.market.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by guoqingwen on 2016/07/28 14:51
 */
public class LoadDataUtil {

    private static final String KEY = "0be9137ec55760985fa8e5f1088193e1";
    private static LoadDataUtil ourInstance = new LoadDataUtil();
    private OkHttpClient httpClient;
    private Handler delivery;
    private ProgressDialog dialog;

    private LoadDataUtil() {
        httpClient = HttpClientUtil.getInstance().getClient();
        delivery = new Handler(Looper.getMainLooper());
    }

    public static LoadDataUtil getInstance() {
        return ourInstance;
    }

    public void loadData(final Context context, final String url, Map<String, Object> map, String loadText, final ResultCallBack callBack) {
        if (!NetUtil.getInstance().isConnection()) {
            if (callBack != null) {
                callBack.onFinish();
            }
            return;
        }
        showDialog(context, loadText);
        LogUtil.i("url=" + url);
        Request request = new Request.Builder().url(url).post(getParams(map)).build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                delivery.post(new Runnable() {
                    @Override
                    public void run() {
                        LogUtil.i("request fail--" +url.substring(url.lastIndexOf("/"))+ e);
                        dismissDialog();
                        if (callBack != null) {
                            callBack.onFinish();
                            callBack.onError();
                            ToastUtil.showToast("请求失败");
                        }
                    }
                });
            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                try {
                    final String result = response.body().string();
                    delivery.post(new Runnable() {
                        @Override
                        public void run() {
                            dismissDialog();
                            LogUtil.i("success--"+url.substring(url.lastIndexOf("/")) + result);
                            if (callBack != null) {
                                callBack.onFinish();
                                callBack.onSuccess(result);
                                try {
                                    callBack.onSuccess(new JSONObject(result));
                                } catch (JSONException e) {
                                    callBack.onError();
                                    e.printStackTrace();
                                    return;
                                }
                                JsonData data = new JsonData(result);
                                if (data.isSuccess()) {
                                    callBack.onSuccess(data);
                                } else {
                                    if (!callBack.onFail() && data.getData().length() > 0) {
                                        ToastUtil.showToast(data.getData());
                                    }
                                }
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    delivery.post(new Runnable() {
                        @Override
                        public void run() {
                            dismissDialog();
                            ToastUtil.showToast("服务器异常");
                            if (callBack != null) {
                                callBack.onFinish();
                                callBack.onError();
                            }
                        }
                    });
                }
            }
        });
    }

    private RequestBody getParams(Map<String, Object> map) {
        FormBody.Builder builder = new FormBody.Builder();
        if (map != null&&!map.isEmpty()) {
//            map = new HashMap<>();
            String time = System.currentTimeMillis() / 1000 + "";
            map.put("sign", sign(map, time));
            map.put("time", time);
        } else {
            map=new HashMap<>();
        }
//        map.put("api_version",1);
//        map.put("device_type","android");
        StringBuffer buffer = new StringBuffer();
        Set<Map.Entry<String, Object>> set = map.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            builder.add(entry.getKey(), String.valueOf(entry.getValue()));
            buffer.append(entry.getKey() + "=" + entry.getValue());
            if (iterator.hasNext()) {
                buffer.append("&");
            }
        }
        LogUtil.i("params=" + buffer.toString());
        return builder.build();
    }

    private String sign(Map<String, Object> params, String time) {
        Map<String, Object> map = new TreeMap<>(new MapKeyComparator());
        if (params != null && !params.isEmpty()) {
            map.putAll(params);
        }
        Set<String> set = map.keySet();
        Iterator<String> iterator = set.iterator();
        StringBuffer buffer = new StringBuffer();
        String key, value;
        while (iterator.hasNext()) {
            key = iterator.next();
            value = String.valueOf(map.get(key));
            buffer.append(key + "=" + value + "&");
        }
        String result = buffer.toString() + "time=" + time + "&salt=" + KEY;
        result = URLEncoder.encode(result);
        return MD5.MD5Encode(result).toUpperCase();
    }

    private void showDialog(Context context, String msg) {
        if (msg == null) {
            return;
        }
        try {
            if (dialog != null && dialog.isShowing()) {
                return;
            }
            dialog = ProgressDialog.show(context, null, msg, true, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dismissDialog() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                dialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class MapKeyComparator implements Comparator<String> {
        @Override
        public int compare(String lhs, String rhs) {
            return lhs.compareTo(rhs);
        }
    }
}
