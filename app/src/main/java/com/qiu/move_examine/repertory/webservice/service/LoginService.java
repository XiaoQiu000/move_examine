package com.qiu.move_examine.repertory.webservice.service;

import com.qiu.move_examine.repertory.webservice.request.LoginRequest;
import com.qiu.move_examine.repertory.webservice.response.CommonResponse;
import com.satsoftec.frame.repertory.remote.BaseWebService;
import com.satsoftec.frame.repertory.remote.WebTask;

/**
 * @author Mr.Qiu
 */
public class LoginService extends BaseWebService {
    /**
     * 通过账号密码登录
     *
     * @param phone
     * @param password
     * @return
     */
    public final static String USER_LOGIN_BY_ACCOUNT = "/mobileInventory/login/loginApp";

    public WebTask<CommonResponse> userLoginByPhone(String account, String password) {
        LoginRequest request = new LoginRequest();
        request.setPerNo(account);
        request.setPerPwd(password);
        return request(USER_LOGIN_BY_ACCOUNT, request, null, CommonResponse.class);
    }
}
