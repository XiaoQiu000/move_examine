package com.qiu.move_examine.contract;

import com.qiu.move_examine.repertory.db.bean.NoticeInfo;
import com.satsoftec.frame.executer.BaseExecuter;
import com.satsoftec.frame.presenter.BasePresenter;

import java.util.List;

/**
 * @author Mr.Qiu
 */
public class MessageContract {
    public interface MessageExecute extends BaseExecuter {
        void loadNotice();

        void loadDeleteNotice();

        void loadNoReadCount();
    }

    public interface MessagePresenter extends BasePresenter<MessageExecute> {
        void noticeResult(List<NoticeInfo> list);

        void deleteNoticeResult();

        void noReadCountResult(List<NoticeInfo> list);
    }
}
