package com.itheima.dfy.myqq34.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.itheima.dfy.myqq34.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by dfy on 29/3/2017.
 * 登录页面
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.urseNmae)
    EditText mUrseNmae;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.btn_login)
    Button   mBtnLogin;
    @BindView(R.id.tv_new_user)
    TextView mTvNewUser;

    @Override
    protected void init() {
    }
    @Override
    public int getLayoutResId() {
        return R.layout.activity_logined;
    }

    @OnClick({R.id.btn_login, R.id.tv_new_user})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                break;
            case R.id.tv_new_user:   //点击跳转到注册页面;
                go2Activity(RegisterActivity.class);
                break;
        }
    }
}
