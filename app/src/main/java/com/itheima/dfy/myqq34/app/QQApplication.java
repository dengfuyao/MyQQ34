package com.itheima.dfy.myqq34.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

import java.util.Iterator;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/**
 * Created by dfy on 29/3/2017.
 */

public class QQApplication extends Application {
    private static final String TAG = "QQApplication";
    private Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        EMOptions options = new EMOptions();
// 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        appContext = this;
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);  //
// 如果APP启用了远程的service，此application:onCreate会被调用2次
// 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
// 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回

        if (processAppName == null ||!processAppName.equalsIgnoreCase(appContext.getPackageName())) {
            Log.e(TAG, "enter the service process!");

            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }
//初始化
        EMClient.getInstance().init(this, options);
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
        initBomb();
    }

    private void initBomb() {
        //提供以下两种方式进行初始化操作：

        //第一：默认初始化
       // Bmob.initialize(this, "Your Application ID");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        BmobConfig config =new BmobConfig.Builder(this)
       //设置appkey
        .setApplicationId("fc470eca6316208ee98209427c10d44d")  //填写用户的key,在服务器获取
        ////请求超时时间（单位为秒）：默认15s
       .setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
       .setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
       .setFileExpiration(2500)
      .build();
        Bmob.initialize(config);
    }

    /**
     * 如何获取processAppName请参考以下方法。
     * @param pID
     * @return processAppName
     */
    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();//获取进程 的集合;
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        //遍历集合对比每个进程的ID,如果ID与这个应用的ID相同,那么就获取到应用的进程名字
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }
}
