package com.itheima.dfy.myqq34.model;

import cn.bmob.v3.BmobUser;

/**
 * Created by dfy on 2/4/2017.
 */

public class User extends BmobUser {
    private String height;
    private String faceScore;

    public User(String height, String faceScore) {
        this.height = height;
        this.faceScore = faceScore;
    }
    public String getHeight() {
        return height;
    }
    public String getFaceScore() {
        return faceScore;
    }
    public void setHeight(String height) {
        this.height = height;
    }
    public void setFaceScore(String faceScore) {
        this.faceScore = faceScore;
    }


}
