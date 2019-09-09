package com.qiu.move_examine.common;

import android.app.Application;
import android.text.TextUtils;

import com.qiu.move_examine.common.base.ApplicationEx;
import com.qiu.move_examine.repertory.db.AppDbInfo;
import com.qiu.move_examine.repertory.webservice.AppWsInfo;
import com.satsoftec.frame.SFrame;
import com.satsoftec.frame.declare.SAppDeclare;
import com.satsoftec.frame.declare.SDatabaseDeclare;
import com.satsoftec.frame.declare.SWebServiceDeclare;
import com.satsoftec.frame.repertory.dbTool.DatabaseInfo;
import com.satsoftec.frame.repertory.remote.WebServiceInfo;
import com.satsoftec.frame.util.AndroidUtil;

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
}
