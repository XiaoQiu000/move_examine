package com.qiu.move_examine.executer;

import com.qiu.move_examine.contract.LoginContract;
import com.qiu.move_examine.repertory.webservice.response.ConnectResponse;
import com.qiu.move_examine.repertory.webservice.response.QueryResponse;
import com.qiu.move_examine.repertory.webservice.service.CommonService;
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
        WebServiceManage.getService(CommonService.class).userLoginByPhone(account, password).setCallback(new SCallBack<QueryResponse>() {
            @Override
            public void callback(boolean isok, String msg, QueryResponse res) {
                loginPresenter.loginResult(isok, msg, res, account, password);
            }
        });
    }

    @Override
    public void connect(final String account, final String password) {
        WebServiceManage.getService(CommonService.class).connectInterface().setCallback(new SCallBack<ConnectResponse>() {
            @Override
            public void callback(boolean isok, String msg, ConnectResponse res) {
                loginPresenter.connectResult(isok, msg, res, account, password);
            }
        });
    }
}
