package com.mvp.cn;

import android.app.Application;

import com.mvp.cn.router.RouterManager;
import com.tencent.mars.xlog.Log;


/**
 * @author iqiao
 * app入口类
 */

public class BaseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        //注册路由框架
        RouterManager.getInstance().init(this);


        //当前测试代码的设备是夜神模拟器Android7.1.2、Android5.1,三星 SM-G8750 Android8.0，
//        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {
//            //启动保活进程（前台进程保活法） 1
//            startService(new Intent(this, ForegroundService.class));
//        } else {
//            //  拉活 2
//            startService(new Intent(this, StickyService.class));
//        }
        //三星 SM-G8750 Android8.0 手动结束掉应用，jobservice也结束了
//        JobService.startJob(this);

    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.appenderClose();
    }


}



