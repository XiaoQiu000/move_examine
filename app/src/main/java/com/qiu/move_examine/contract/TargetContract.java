package com.qiu.move_examine.contract;

import com.qiu.move_examine.repertory.webservice.response.ConnectResponse;
import com.qiu.move_examine.repertory.webservice.response.QueryResponse;
import com.satsoftec.frame.executer.BaseExecuter;
import com.satsoftec.frame.presenter.BasePresenter;

/**
 * @author Mr.Qiu
 */
public class TargetContract {
    public interface TargetExecute extends BaseExecuter {
        void loadTargetList(String condition, int page, int pageSize);
    }

    public interface TargetPresenter extends BasePresenter<TargetExecute> {
        void targetListResult(boolean isok, String msg, QueryResponse res);
    }
}
