package com.qiu.move_examine.executer;

import com.qiu.move_examine.common.AppContext;
import com.qiu.move_examine.contract.MessageContract;
import com.qiu.move_examine.repertory.db.bean.NoticeInfo;
import com.satsoftec.frame.repertory.dbTool.DatabaseManage;

import java.util.List;

/**
 * @author Mr.Qiu
 */
public class MessageWorker implements MessageContract.MessageExecute {
    private MessageContract.MessagePresenter presenter;

    public MessageWorker(MessageContract.MessagePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loadNotice() {
        List<NoticeInfo> list;
        list = DatabaseManage.getList(NoticeInfo.class, "ownerId=" + AppContext.self().getUserInfo().getId() + " order by createDate desc");
        if (list != null) {
            presenter.noticeResult(list);
        }
    }

    @Override
    public void loadDeleteNotice() {
        DatabaseManage.delete(NoticeInfo.class, "ownerId=" + AppContext.self().getUserInfo().getId());
        presenter.deleteNoticeResult();
    }
}
