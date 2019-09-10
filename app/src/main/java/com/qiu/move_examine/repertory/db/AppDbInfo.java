package com.qiu.move_examine.repertory.db;


import android.support.annotation.NonNull;

import com.qiu.move_examine.repertory.db.bean.JsonBeanInfo;
import com.qiu.move_examine.repertory.db.bean.MessageInfo;
import com.satsoftec.frame.repertory.dbTool.DatabaseInfo;


/**
 * @author Mr.Qiu
 * @date 16/1/10
 */
public class AppDbInfo extends DatabaseInfo {
    private String dbName;

    @Override
    public String getDbPath() {
        return dbName;
    }

    @Override
    public int getDbVersion() {
        return 1;
    }

    @Override
    public Class<?>[] getBeanClass() {
        return new Class<?>[]{
                JsonBeanInfo.class,
                MessageInfo.class,
        };
    }

    public AppDbInfo(@NonNull String dbName) {
        this.dbName = dbName;
    }
}
