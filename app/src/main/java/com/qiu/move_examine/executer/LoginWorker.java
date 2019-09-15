package com.qiu.move_examine.executer;

import com.qiu.move_examine.contract.LoginContract;
import com.qiu.move_examine.repertory.webservice.response.LoginResponse;
import com.qiu.move_examine.repertory.webservice.service.LoginService;
import com.satsoftec.frame.repertory.remote.WebServiceManage;
import com.satsoftec.frame.repertory.remote.callback.SCallBack;

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
        WebServiceManage.getService(LoginService.class).userLoginByPhone(account, password).setCallback(new SCallBack<LoginResponse>() {
            @Override
            public void callback(boolean isok, String msg, LoginResponse res) {
                loginPresenter.loginResult(isok, msg,res, account, password);
            }
        });
    }
}
