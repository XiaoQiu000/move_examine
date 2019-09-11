package com.qiu.move_examine.executer;

import com.qiu.move_examine.common.ClientConstant;
import com.qiu.move_examine.contract.LoginContract;
import com.qiu.move_examine.repertory.webservice.response.CommonResponse;
import com.qiu.move_examine.repertory.webservice.service.LoginService;
import com.satsoftec.frame.repertory.remote.WebServiceManage;
import com.satsoftec.frame.repertory.remote.callback.SCallBack;
import com.satsoftec.frame.util.SharedPreferenceUtil;

/**
 * @author Mr.Qiu
 */
public class LoginWorker implements LoginContract.LoginExecute {
    private LoginContract.LoginPresenter loginPresenter;

    public LoginWorker(LoginContract.LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void loginByAccount(final String account, final String password) {
        WebServiceManage.getService(LoginService.class).userLoginByPhone(account, password).setCallback(new SCallBack<CommonResponse>() {
            @Override
            public void callback(boolean isok, String msg, CommonResponse res) {
                loginPresenter.loginResult(isok, res, account, password);
            }
        });
    }
}
