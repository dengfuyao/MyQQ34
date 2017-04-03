package com.itheima.dfy.myqq34.views;

/**
 * Created by dfy on 31/3/2017.
 * 注册账号的view交互
 */

public interface RegisterView {
    void onUserNmaeErr();  //账号错误;

    void onPassword();  //密码错误;

    void on2PasswordNotEquals();//两次密码不一样;

    void onRegisterBmobSucceed();  //创建Bmob成功

    void onRegisterBombFailure(int e);   //注册失败

    void onUserNameAlreadyTaken(String username);

    void onRegisterHuanxinSucceed();  //注册环信成功

    void onRegisterHuanxinFailuer();   //注册环信失败
}
