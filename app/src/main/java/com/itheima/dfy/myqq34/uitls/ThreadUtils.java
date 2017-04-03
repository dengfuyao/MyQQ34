package com.itheima.dfy.myqq34.uitls;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dfy on 2/4/2017.
 */

public class ThreadUtils {
    //创建Handel发送消息
    public static Handler mHandler = new Handler(Looper.getMainLooper());  //指定在主线程创年handle对象;
    //创建主线程
    public static void onUiThread( Runnable runnable){  //主线程
        mHandler.post(runnable);
    }
    //创建线程池
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();//创建单一的线程池
    //子线程
    public static void onBgThread(Runnable runnable){
        executorService.execute(runnable);
    }

}
