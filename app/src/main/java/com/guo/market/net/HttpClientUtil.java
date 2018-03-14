package com.guo.market.net;

import okhttp3.OkHttpClient;

/**
 * Created by 任少东 on 2016/11/10 17:46
 */
public class HttpClientUtil {

    private static OkHttpClient client;
    private static HttpClientUtil ourInstance = new HttpClientUtil();

    public static HttpClientUtil getInstance() {
        return ourInstance;
    }

    private HttpClientUtil() {
        client=new OkHttpClient();
    }

    public OkHttpClient getClient(){
        return client;
    }
}
