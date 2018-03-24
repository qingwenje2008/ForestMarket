package com.forest.market.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;


public class User implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * user_id : 34
     * mobile : 18848980350
     * avatar :
     * residence_address :
     * real_name :
     * nickname :
     * register_time : 2017-03-13 18:14:17
     * driver_school_id :
     * is_certificate : 0
     * sex : male
     * id_card :
     * password : 123456
     * foucus_num :
     * by_foucus_num :
     */

    @JSONField(name = "user_id")
    private String userId;
    @JSONField(name = "mobile")
    private String mobile;
    @JSONField(name = "avatar")
    private String avatar;
    @JSONField(name = "residence_address")
    private String residenceAddress;
    @JSONField(name = "real_name")
    private String realName;
    @JSONField(name = "nickname")
    private String nickname;
    @JSONField(name = "register_time")
    private String registerTime;
    @JSONField(name = "driver_school_id")
    private String driverSchoolId;
    @JSONField(name = "is_certificate")
    private String isCertificate;
    @JSONField(name = "sex")
    private String sex;
    @JSONField(name = "id_card")
    private String idCard;
    @JSONField(name = "password")
    private String password;
    @JSONField(name = "foucus_num")
    private String foucusNum;
    @JSONField(name = "by_foucus_num")
    private String byFoucusNum;
    @JSONField(name = "is_student")
    private String isStudent;


    private String gold_balance;

    private int integral_balance;
    /**
     * images : ["http://jiaxiao.181858.com/Uploads/send_article/2017-03-20/14899813516083.png"]
     * article_id : 27
     * is_focus : 0
     */
    private int present;

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }

    @JSONField(name = "article_id")
    private String articleId;
    @JSONField(name = "is_focus")
    private int isFocus;
    @JSONField(name = "images")
    private List<String> images;

    public int getIntegral_balance() {
        return integral_balance;
    }

    public void setIntegral_balance(int integral_balance) {
        this.integral_balance = integral_balance;
    }

    public String getGold_balance() {
        return gold_balance;
    }

    public void setGold_balance(String gold_balance) {
        this.gold_balance = gold_balance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getDriverSchoolId() {
        return driverSchoolId;
    }

    public void setDriverSchoolId(String driverSchoolId) {
        this.driverSchoolId = driverSchoolId;
    }

    public String getIsCertificate() {
        return isCertificate;
    }

    public void setIsCertificate(String isCertificate) {
        this.isCertificate = isCertificate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFoucusNum() {
        return foucusNum;
    }

    public void setFoucusNum(String foucusNum) {
        this.foucusNum = foucusNum;
    }

    public String getByFoucusNum() {
        return byFoucusNum;
    }

    public void setByFoucusNum(String byFoucusNum) {
        this.byFoucusNum = byFoucusNum;
    }


    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public int getIsFocus() {
        return isFocus;
    }

    public void setIsFocus(int isFocus) {
        this.isFocus = isFocus;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getIsStudent() {
        return isStudent;
    }

    public void setIsStudent(String isStudent) {
        this.isStudent = isStudent;
    }
}
