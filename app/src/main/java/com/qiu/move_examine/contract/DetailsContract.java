package com.qiu.move_examine.contract;

import com.qiu.move_examine.repertory.webservice.response.QueryResponse;
import com.satsoftec.frame.executer.BaseExecuter;
import com.satsoftec.frame.presenter.BasePresenter;

public class DetailsContract {
    public interface DetailsExecute extends BaseExecuter {
        void loadDetails(String id);
    }

    public interface DetailsPresenter extends BasePresenter<DetailsExecute> {
        void detailsResult(boolean isok, String msg, QueryResponse res);
    }
}
