package com.qiu.move_examine.executer;

import com.qiu.move_examine.contract.TargetContract;
import com.qiu.move_examine.repertory.webservice.response.ConnectResponse;
import com.qiu.move_examine.repertory.webservice.response.QueryResponse;
import com.qiu.move_examine.repertory.webservice.service.CommonService;
import com.satsoftec.frame.repertory.remote.WebServiceManage;
import com.satsoftec.frame.repertory.remote.callback.SCallBack;

/**
 * @author Mr.Qiu
 */
public class TargetWorker implements TargetContract.TargetExecute {
    private TargetContract.TargetPresenter presenter;

    public TargetWorker(TargetContract.TargetPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loadTargetList(int page, int pageSize) {
        WebServiceManage.getService(CommonService.class).targetList(page, pageSize).setCallback(new SCallBack<QueryResponse>() {
            @Override
            public void callback(boolean isok, String msg, QueryResponse res) {
                presenter.targetListResult(isok, msg, res);
            }
        });
    }
}
