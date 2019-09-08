package com.qiu.move_examine.repertory.webservice;

import com.qiu.move_examine.common.AppContext;
import com.qiu.move_examine.common.base.Response;
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
    private static final int TOKEN_ERROR = 100;

    @Override
    public Class<?>[] getInterFaceClass() {
        return new Class<?>[]{

        };
    }

    private static final String SERVEL_URL = "http://testserver.51tiexin.com:8085/";

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
