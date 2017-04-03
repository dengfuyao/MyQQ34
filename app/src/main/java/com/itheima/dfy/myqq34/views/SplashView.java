package com.itheima.dfy.myqq34.views;

/**
 * Created by dfy on 29/3/2017.
 * 对状态的一个处理接口;提供成功和失败的方法;让要处理的activity实现这个接口;
 */

public interface SplashView {
    /**
     * 已经登录的处理
     */
    void onHasLogin();

    /**
     * 没有登录的处理;
     */
    void onHasNotLogin();
}
