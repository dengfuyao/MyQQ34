package com.itheima.dfy.myqq34.ui.activity;

import com.itheima.dfy.myqq34.R;
import com.itheima.dfy.myqq34.presenter.SplashPresenterImp;
import com.itheima.dfy.myqq34.views.SplashView;

public class SplashActivity extends BaseActivity implements SplashView{

private SplashPresenterImp mSplashPresenterImp;
    @Override
    protected void init() {
        mSplashPresenterImp = new SplashPresenterImp(this);
        mSplashPresenterImp.checkLoginStart();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    public void onHasLogin() {
        //已经登录,直接跳转到主界面;
        go2Activity(MainActivity.class);
        finish();

    }

    @Override
    public void onHasNotLogin() {
        //跳转到登录界面;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                go2Activity(LoginActivity.class);
                finish();
            }
        },1500);
    }
}
