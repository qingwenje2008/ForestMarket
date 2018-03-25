package com.forest.market.entity;

/**
 * Created by qingwenguo on 2018/3/25.
 */

public class Comment {
    private String nickName;
    private String content;

    public Comment(String nickName, String content) {
        this.nickName = nickName;
        this.content = content;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
