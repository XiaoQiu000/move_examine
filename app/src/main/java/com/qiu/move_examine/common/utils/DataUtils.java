package com.qiu.move_examine.common.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

}
