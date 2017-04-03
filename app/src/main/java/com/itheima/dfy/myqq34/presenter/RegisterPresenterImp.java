package com.itheima.dfy.myqq34.presenter;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.itheima.dfy.myqq34.model.User;
import com.itheima.dfy.myqq34.uitls.StringUitls;
import com.itheima.dfy.myqq34.uitls.ThreadUtils;
import com.itheima.dfy.myqq34.views.RegisterView;

import java.util.Random;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dfy on 31/3/2017.
 * 实现注册的逻辑绑定到view层;
 */

public class RegisterPresenterImp implements RegisterPresenter{
    private RegisterView mRegisterView ;

    public RegisterPresenterImp(RegisterView registerView) {
        mRegisterView = registerView;
    }

    @Override
    //开始注册;
    public void register(String usenName, String password, String confirm) {
        //先对文本进行校验;
        if (StringUitls.isValidUserName(usenName)){
            if (StringUitls.isValidPassword(password)){
                if (password.equals(confirm)){
                    //开始在bmob注册;
                    registerBmob(usenName,password);
                }else{
                    //两次密码不一样
                    mRegisterView.on2PasswordNotEquals();
                }
            }else{
                mRegisterView.onPassword();
            }

        }else {
            mRegisterView.onUserNmaeErr();
        }

    }

    private void registerBmob(final String userName, final String password) {
        //随机设置用户的身高和颜值分;
        User user = new User(String.valueOf(160+new Random().nextInt(30)),String.valueOf(new Random().nextInt(10)));
        user.setUsername(userName);
        user.setPassword(password);
        user.signUp(new SaveListener<User>() {  //发起注册,注册失败会爆出异常;将注册后的UI交给view层处理;
            @Override
            public void done(User user, BmobException e) {
                //如果没有异常就注册成功
                if (e==null){
                    mRegisterView.onRegisterBmobSucceed();
                    //注册bmob成功后开支执行注册环信
                    registerHuanxin(userName,password);
                }else if (e.getErrorCode()==202){
                    mRegisterView.onUserNameAlreadyTaken(userName);
                }
                else{
                    //参见详情http://docs.bmob.cn/data/Android/g_errorcode/doc/index.html#RESTAPI%E9%94%99%E8%AF%AF%E7%A0%81%E5%88%97%E8%A1%A8
                    mRegisterView.onRegisterBombFailure(e.getErrorCode());  //注册失败
                }
            }
        });
    }
    private void registerHuanxin(final String userName, final String password) {
        //注册失败会抛出HyphenateException
        //注册环信的法是同步方法要在子线程中执行而注册成功后回调后处理UI要交给主线程,所以这里去创建一个工具类,建立线程池来处理;
        ThreadUtils.onBgThread(new Runnable() {
            @Override
            public void run() {
                //在子线程中注册环信
                try {
                    EMClient.getInstance().createAccount(userName, password);//同步方法
                    //注册成功后交给主线程处理UI
                    ThreadUtils.onUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mRegisterView.onRegisterHuanxinSucceed();
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    ThreadUtils.onUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mRegisterView.onRegisterHuanxinFailuer();
                        }
                    });
                }
            }
        });

    }
}
