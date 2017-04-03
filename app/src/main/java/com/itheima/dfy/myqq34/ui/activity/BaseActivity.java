package com.itheima.dfy.myqq34.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

import butterknife.ButterKnife;

/**
 * Created by dfy on 29/3/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
     public Handler mHandler = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        init();
        listener();
    }

    /**
     * 影藏输入框;
     */
    protected void hidesoftkeyBoard(){
        InputMethodManager methodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        methodManager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
    }
    protected  void listener(){};

    protected abstract void init();


    public abstract int getLayoutResId();
    protected void go2Activity(Class activity){
        Intent intent = new Intent(this,activity);
        startActivity(intent);
    }

}
