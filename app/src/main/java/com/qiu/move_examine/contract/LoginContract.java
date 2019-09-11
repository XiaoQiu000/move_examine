package com.qiu.move_examine.contract;

import com.qiu.move_examine.repertory.webservice.response.CommonResponse;
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
         *  @param isok 是否成功
         * @param res  返回数据
         * @param account
         * @param password
         */
        void loginResult(boolean isok, CommonResponse res, String account, String password);
    }
}
