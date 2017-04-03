package com.itheima.dfy.myqq34.presenter;

import com.hyphenate.chat.EMClient;
import com.itheima.dfy.myqq34.views.SplashView;

/**
 * Created by dfy on 29/3/2017.
 * 处理页面跳转的逻辑实现,
 */

public class SplashPresenterImp implements SplashPresenter {

private SplashView mSplashView;

    public SplashPresenterImp(SplashView splashView) {
        mSplashView = splashView;
    }

    @Override
    public void checkLoginStart() {
        //先判断用户是否已经登录了
        boolean isLongined = getLoginStart();
        if (isLongined){
            //逻辑层不处理UI,将UI交给view层处理
            mSplashView.onHasLogin();

        }else{
            mSplashView.onHasNotLogin();
        }
    }

    public boolean getLoginStart() {
        //TODO : 通过集成环信获取登录状态;
        //登录必须要先注册;
        return EMClient.getInstance().isLoggedInBefore()&&EMClient.getInstance().isConnected();

    }
}
