package com.satsoftec.frame;

import com.google.gson.Gson;
import com.satsoftec.frame.declare.SAppDeclare;
import com.satsoftec.frame.declare.SDatabaseDeclare;
import com.satsoftec.frame.declare.SWebServiceDeclare;
import com.satsoftec.frame.repertory.dbTool.DatabaseManage;
import com.satsoftec.frame.repertory.remote.WebServiceManage;
import com.satsoftec.frame.util.SharedPreferenceUtil;

import org.xutils.x;

/**
 * Created by 10124 on 2017/7/26.
 */
public class SFrame {
    private static boolean inited = false;

    private static Gson gson;

    public static void init(SAppDeclare context) {
        if (!inited) {
            inited = true;
            //初始化x框架
            x.Ext.init(context.getApplication());

            SharedPreferenceUtil.init(context.getApplication());

            //如果需要初始化数据库，在这里初始化
            if (context instanceof SDatabaseDeclare) {
                SDatabaseDeclare declare = (SDatabaseDeclare) context;
                DatabaseManage.init(context.getApplication(), declare.getDatabaseInfo());
            }

            if (context instanceof SWebServiceDeclare) {
                SWebServiceDeclare declare = (SWebServiceDeclare) context;
                WebServiceManage.init(context.getApplication(), declare.getWebServiceInfo());
            }
        }
    }

    public static Gson getGson(){
        if(SFrame.gson==null){
            SFrame.gson=new Gson();
        }
        return SFrame.gson;
    }
}
