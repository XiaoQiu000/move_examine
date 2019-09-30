package com.qiu.move_examine.repertory.webservice;

import com.qiu.move_examine.common.base.Response;
import com.qiu.move_examine.repertory.db.bean.JsonBeanInfo;
import com.qiu.move_examine.repertory.webservice.service.CommonService;
import com.satsoftec.frame.SFrame;
import com.satsoftec.frame.repertory.remote.ResultCheck;
import com.satsoftec.frame.repertory.remote.ResultConvertor;
import com.satsoftec.frame.repertory.remote.ResultHandle;
import com.satsoftec.frame.repertory.remote.WebServiceInfo;

import java.lang.reflect.Type;

/**
 * @author 10124
 * @date 2017/7/27
 */
public class AppWsInfo extends WebServiceInfo {

    @Override
    public Class<?>[] getInterFaceClass() {
        return new Class<?>[]{
                CommonService.class,
                JsonBeanInfo.class
        };
    }

    private static final String SERVEL_URL = "http://47.98.216.253:80/dataRequ/connAndQry";
//    private static final String SERVEL_URL = "http://56.32.3.11:9080/drs/json-rpc";

    @Override
    public String getServerUrl() {
        return SERVEL_URL;
    }

    @Override
    public ResultConvertor getDefaultResultConvertor() {
        return new ResultConvertor() {

            @Override
            public <T> T convertor(String res, Type typeOfT) {
                return SFrame.getGson().fromJson(res, typeOfT);
            }
        };
    }

    @Override
    public ResultHandle getDefaultResultHandle() {
        return new ResultHandle() {
            @Override
            public <T> String handle(T t) {
                if (t == null) {
                    return "服务器无法连接,请检查网络..";
                }
                if (t instanceof Response) {
                    ResultCheck.checkResult(((Response) t).getCode() != null && ((Response) t).getCode() == 0, ((Response) t).getMsg());
                }
                return "操作成功！";
            }
        };
    }
}
