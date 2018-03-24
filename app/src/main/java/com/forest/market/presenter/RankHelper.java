package com.forest.market.presenter;

import android.content.Context;


import com.forest.market.LocalData;
import com.forest.market.configs.UrlConfig;
import com.forest.market.entity.User;
import com.forest.market.net.JsonData;
import com.forest.market.net.LoadDataUtil;
import com.forest.market.net.ResultCallBack;
import com.forest.market.presenter.interfaceview.IRankView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by guo on 2016/11/17 14:43
 */
public class RankHelper  {

    private Context context;
    private IRankView iRankView;
    public RankHelper(Context context){

    }

    public void search(String key, int page) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", key);
        params.put("my_uid", LocalData.getUid());
        params.put("page", page);
        params.put("pagesize", 10);
        LoadDataUtil.getInstance().loadData(context, UrlConfig.ROOT_URL + "index.php/App/Public/search", params, null, new ResultCallBack() {
            @Override
            public void onSuccess(JsonData data) {
//                List<User> list = data.getList(User.class);
//                if (userListView != null) {
//                    userListView.onSuccess(list);
//                }
            }

            @Override
            public void onFinish() {
//                if (userListView!=null){
//                    userListView.onFinish();
//                }
            }
        });
    }

    public void onDestroy() {

    }
}
