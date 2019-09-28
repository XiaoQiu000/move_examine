package com.qiu.move_examine.presenter.receiver;


import android.app.ActivityManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.qiu.move_examine.presenter.service.NoticeService;

import java.util.List;

public class NoticeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, NoticeService.class);
        if (isAppForeground(context) || Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            context.startService(i);
        } else {
            context.startForegroundService(i);
        }
    }

    /**
     * 判断app是否处于前台
     *
     * @param context
     * @return
     */
    public static boolean isAppForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Service.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfoList = activityManager.getRunningAppProcesses();
        if (runningAppProcessInfoList == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo processInfo : runningAppProcessInfoList) {
            if (processInfo.processName.equals(context.getPackageName()) &&
                    processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }
}
