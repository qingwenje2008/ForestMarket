package com.forest.market;


import com.forest.market.entity.User;
import com.forest.market.util.FileUtil;
import com.forest.market.util.ToastUtil;

public class LocalData {

    private static User user;

    public static User getUser() {
        if (user == null) {
            user = (User) FileUtil.read(App.getContext(), "user");
        }
        return user;
    }

    public static void setUser(User user) {
        LocalData.user = user;
        FileUtil.save(App.getContext(), "user", user);
    }

    public static boolean deleteUser() {
        user = null;
        return FileUtil.delete(App.getContext(), "user");
    }


    public static String getUid() {
        return getUser().getUserId();
    }

    public static boolean isLogin() {
        return getUser() != null;
    }

    public static void loginTip() {
        if (!isLogin() || getUid() == null || getUid().equals("")) {
            ToastUtil.showToast("请登录");
            return;
        }

    }


    public static boolean isDebug(String ver) {
        return "321".equals(ver);
    }

    public static void setCertificate(String type) {
        user.setIsCertificate(type);
        setUser(user);
    }

    public static void setBalance(String balance) {
        user.setIntegral_balance(Integer.valueOf(balance));
        setUser(user);
    }

    public static void addBalance(int balance) {
        user.setIntegral_balance(user.getIntegral_balance() + balance);
        setUser(user);
    }

    public static void setPresent(int present) {
        user.setPresent(present);
        setUser(user);
    }

}
