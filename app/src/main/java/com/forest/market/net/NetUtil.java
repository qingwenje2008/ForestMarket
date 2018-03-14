package com.forest.market.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;

import com.forest.market.App;
import com.forest.market.util.ToastUtil;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;


/**
 * Created by guoqingwen on 2016/07/28 14:27
 */
public class NetUtil {

    private static NetUtil ourInstance = new NetUtil();
    private ConnectivityManager cm;

    private NetUtil() {
        cm = (ConnectivityManager) App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static NetUtil getInstance() {
        return ourInstance;
    }

    public boolean isConnection() {
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null || !info.isConnected()) {
            if (Thread.currentThread().getName().equals("main")) {
                ToastUtil.showToast("无网络连接");
            } else {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast("无网络连接");
                    }
                });
            }
            return false;
        }
        return true;
    }

    /**
     * 获取本机IP
     * @return
     */

    public String getDeviceIP() {
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null) {
            return null;
        }
        if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            WifiManager wifiManager = (WifiManager) App.getContext().getSystemService(Context.WIFI_SERVICE);
//            if (!wifiManager.isWifiEnabled()) {
//                wifiManager.setWifiEnabled(true);
//            }
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int ipAddress = wifiInfo.getIpAddress();
            String ip = intToIp(ipAddress);
            return ip;
        } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            try {
                for (Enumeration<NetworkInterface> en = NetworkInterface
                        .getNetworkInterfaces(); en.hasMoreElements(); ) {
                    NetworkInterface inft = en.nextElement();
                    for (Enumeration<InetAddress> enumIpAddr = inft
                            .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            return inetAddress.getHostAddress().toString();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private String intToIp(int ipAddress) {

        return (ipAddress & 0xFF) + "." + ((ipAddress >> 8) & 0xFF) + "."
                + ((ipAddress >> 16) & 0xFF) + "." + ((ipAddress >> 24) & 0xFF);
    }
}
