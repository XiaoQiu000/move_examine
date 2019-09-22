package com.qiu.move_examine.executer;

import com.qiu.move_examine.contract.DetailsContract;
import com.qiu.move_examine.repertory.webservice.response.QueryResponse;
import com.qiu.move_examine.repertory.webservice.service.CommonService;
import com.satsoftec.frame.repertory.remote.WebServiceManage;
import com.satsoftec.frame.repertory.remote.callback.SCallBack;

/**
 * @author Mr.Qiu
 * 详细或者目标库详情
 */
public class DetailsWorker implements DetailsContract.DetailsExecute {
    private DetailsContract.DetailsPresenter presenter;

    public DetailsWorker(DetailsContract.DetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loadDetails(String id) {
        WebServiceManage.getService(CommonService.class).targetDetails(id).setCallback(new SCallBack<QueryResponse>() {
            @Override
            public void callback(boolean isok, String msg, QueryResponse res) {
                presenter.detailsResult(isok, msg, res);
            }
        });
    }
}
