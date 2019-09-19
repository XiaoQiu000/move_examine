package com.qiu.move_examine.presenter.receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.qiu.move_examine.common.ClientConstant;
import com.qiu.move_examine.presenter.activity.MainActivity;
import com.satsoftec.frame.util.AndroidUtil;
import com.satsoftec.frame.util.SharedPreferenceUtil;

import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.helper.Logger;
import cn.jpush.android.service.JPushMessageReceiver;

/**
 * @author Mr.Qiu
 */
public class MyJPushMessageReceiver extends JPushMessageReceiver {
    private static final String TAG = "MyReceiver";

    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        super.onMessage(context, customMessage);
        Log.e(TAG, "onMessage: 收到自定义消息" + customMessage);
    }

    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageArrived(context, notificationMessage);
        Log.e(TAG, "onNotifyMessageArrived: 收到通知消息" + notificationMessage);

    }


    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onAliasOperatorResult(context, jPushMessage);
        switch (jPushMessage.getErrorCode()) {
            case 0:
                Log.e(TAG, "onAliasOperatorResult: 设置别名成功");
                SharedPreferenceUtil.saveSharedPreBoolean(ClientConstant.SPREFERENCES_ALIAS, true);
                break;
            case 6002:
                Log.e(TAG, "onAliasOperatorResult: 设置别名失败1");
                SharedPreferenceUtil.saveSharedPreBoolean(ClientConstant.SPREFERENCES_ALIAS, false);
                break;
            default:
                Log.e(TAG, "onAliasOperatorResult: 设置别名失败2");
                SharedPreferenceUtil.saveSharedPreBoolean(ClientConstant.SPREFERENCES_ALIAS, false);
                break;
        }
    }

    @Override
    public void onNotifyMessageOpened(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageOpened(context, notificationMessage);
        Log.e(TAG, "onNotifyMessageOpened: 点击通知" +notificationMessage);
    }
}
