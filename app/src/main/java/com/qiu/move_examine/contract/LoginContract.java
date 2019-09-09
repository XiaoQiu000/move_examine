package com.qiu.move_examine.contract;

import com.qiu.move_examine.repertory.webservice.response.LoginResponse;
import com.satsoftec.frame.executer.BaseExecuter;
import com.satsoftec.frame.presenter.BasePresenter;

/**
 * @author Mr.Qiu
 */
public class LoginContract {
    public interface LoginExecute extends BaseExecuter {
        /**
         * 账号密码登录
         *
         * @param account  账号
         * @param password 密码
         */
        void loginByAccount(String account, String password);
    }

    public interface LoginPresenter extends BasePresenter<LoginExecute> {
        /**
         * 账号密码登录结果
         *
         * @param isok 是否成功
         * @param msg  错误信息
         * @param res  返回数据
         */
        void loginResult(boolean isok, String msg, LoginResponse res);
    }
}
