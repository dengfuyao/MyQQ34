package com.itheima.dfy.myqq34.uitls;

/**
 * Created by dfy on 31/3/2017.
 * 将注册侧账号密码格式规范的工具类
 */

public class StringUitls {
    private static final String USERNAME_REGEX = "^[A-Za-z]\\w{2,19}$";//首位为字母,长度是三到20位;
    private static final String PASSWORD_REGEX    = "^[0-9]{4,20}$";

    public static boolean isValidUserName(String userName){
    return userName.matches(USERNAME_REGEX);
    }
    public static boolean isValidPassword(String password){
        return password.matches(PASSWORD_REGEX);
    }
    
}
