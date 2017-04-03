package com.itheima.dfy.myqq34.ui.activity;

import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.dfy.myqq34.R;
import com.itheima.dfy.myqq34.presenter.RegisterPresenter;
import com.itheima.dfy.myqq34.presenter.RegisterPresenterImp;
import com.itheima.dfy.myqq34.views.RegisterView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by dfy on 30/3/2017.
 */
public class RegisterActivity extends BaseActivity implements RegisterView {
    @BindView(R.id.urseNmae)
    EditText mUrseNmae;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.reconfirmpass)
    EditText mReconfirmpass;
    @BindView(R.id.btn_register)
    Button   mBtnRegister;

    private RegisterPresenter mRegisterPresenter;

    @Override
    protected void init() {
    mRegisterPresenter = new RegisterPresenterImp(this);
        //监听确认密码的完成,执行注册的炒作;
        mReconfirmpass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                //调用注册的方法
                register();
                return true; //消费事件
            }
        });
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_register;
    }
    @OnClick(R.id.btn_register)
    public void onClick() {
        //TODO:给服务器发送注册信息;
        register();
    }
    /**
     * 获取到账号密码,传递出去;
     */
    private void register() {
        String urse = mUrseNmae.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String confirm = mReconfirmpass.getText().toString().trim();
        hidesoftkeyBoard();//隐藏掉输入框
        mRegisterPresenter.register(urse,password,confirm);
    }
    @Override
    public void onUserNmaeErr() {
        mUrseNmae.setError("用户名不规范,3到20位字母或数字,必须以首字母开头");
    }

    @Override
    public void onPassword() {
        mPassword.setError("密码不规范,要求在4到20位");
    }

    @Override
    public void on2PasswordNotEquals() {
        mReconfirmpass.setError("密码不一致");
    }

    @Override
    //注册bmob账号成功
    public void onRegisterBmobSucceed() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    //注册Bmob账号失败
    public void onRegisterBombFailure(int e) {
        Toast.makeText(this, "错误编号参见http://docs.bmob.cn/data/Android/g_errorcode/doc/index.html#RESTAPI%E9%94%99%E8%AF%AF%E7%A0%81%E5%88%97%E8%A1%A8"+e, Toast.LENGTH_SHORT).show();
    }

    @Override
    //用户名已经存在:
    public void onUserNameAlreadyTaken(String username) {
        Toast.makeText(this, username+"已经存在,不能重复注册", Toast.LENGTH_SHORT).show();
    }

   @Override
    public void onRegisterHuanxinSucceed() {
       Toast.makeText(this, "注册环信成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegisterHuanxinFailuer() {
        Toast.makeText(this, "注册环信失败", Toast.LENGTH_SHORT).show();
    }
}
