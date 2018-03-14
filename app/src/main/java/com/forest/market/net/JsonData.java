package com.forest.market.net;

import com.alibaba.fastjson.JSON;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by guoqingwen on 2016/07/28 15:04
 */
public class JsonData {
    private String data;
    private int code;

    public JsonData(String string) {
        try {
            JSONObject object = new JSONObject(string);
            code = object.optInt("code");
            data = object.optString("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean isSuccess() {
        return code == 1;
    }

    public String getData() {
        if (data==null){
            return "";
        }
        return data;
    }

    public JSONObject getJsonData(){
        try {
            return new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T getBean(Class<T> c) {
        try {
            return JSON.parseObject(data, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> List<T> getList(Class<T> c) {
        try {
            return JSON.parseArray(data, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
