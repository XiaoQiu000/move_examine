package com.qiu.move_examine.contract;

import com.qiu.move_examine.repertory.db.bean.MessageInfo;
import com.satsoftec.frame.executer.BaseExecuter;
import com.satsoftec.frame.presenter.BasePresenter;

import java.util.List;

/**
 * @author Mr.Qiu
 */
public class MessageContract {
    public interface MessageExecute extends BaseExecuter {
        void loadMessage();

        void loadDeleteNotice();
    }

    public interface MessagePresenter extends BasePresenter<MessageExecute> {
        void messageResult(List<MessageInfo> list);

        void deleteNoticeResult();
    }
}
