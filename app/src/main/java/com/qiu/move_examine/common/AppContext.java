package com.qiu.move_examine.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.qiu.move_examine.common.base.ApplicationEx;
import com.qiu.move_examine.common.base.BaseActivity;
import com.qiu.move_examine.common.bean.UserInfoBean;
import com.qiu.move_examine.presenter.activity.LoginActivity;
import com.qiu.move_examine.repertory.db.AppDbInfo;
import com.qiu.move_examine.repertory.webservice.AppWsInfo;
import com.satsoftec.frame.SFrame;
import com.satsoftec.frame.declare.SAppDeclare;
import com.satsoftec.frame.declare.SDatabaseDeclare;
import com.satsoftec.frame.declare.SWebServiceDeclare;
import com.satsoftec.frame.repertory.dbTool.DatabaseInfo;
import com.satsoftec.frame.repertory.dbTool.DatabaseManage;
import com.satsoftec.frame.repertory.remote.WebServiceInfo;
import com.satsoftec.frame.util.AndroidUtil;
import com.satsoftec.frame.util.SharedPreferenceUtil;

import java.io.File;

/**
 * @author liuyixian
 * @date 2017/9/20
 */

public class AppContext implements SDatabaseDeclare, SWebServiceDeclare, SAppDeclare {
    private static AppContext self;
    private ApplicationEx application;

    private AppContext() {
    }

    public synchronized static AppContext self() {
        if (self == null) {
            self = new AppContext();
        }
        return self;
    }

    public void init(ApplicationEx app) {
        this.application = app;
        String pname = AndroidUtil.getProcessName(app.getApplicationContext());
        if (TextUtils.isEmpty(pname)) {
            return;
        }
        //初始化APP
        File cacheDir = this.application.getExternalCacheDir();
        if (cacheDir == null) {
            cacheDir = this.application.getCacheDir();
        }
        SFrame.init(this);
    }

    @Override
    public DatabaseInfo getDatabaseInfo() {
        return new AppDbInfo("examine");
    }

    @Override
    public WebServiceInfo getWebServiceInfo() {
        return new AppWsInfo();
    }

    @Override
    public Application getApplication() {
        return this.application;
    }

    /**
     * 退出登录
     */
    public void logout(BaseActivity context) {
        SharedPreferenceUtil.saveSharedPreBoolean(ClientConstant.SPREFERENCES_LOGIN_EXIT, false);
        ((ApplicationEx) AppContext.self().getApplication()).closeAllActivity();
//        //关闭长连接
//        PushClient.close();
        //开启登录界面
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(ClientConstant.SPREFERENCES_LOGIN_EXIT, true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
