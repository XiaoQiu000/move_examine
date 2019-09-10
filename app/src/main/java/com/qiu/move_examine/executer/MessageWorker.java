package com.qiu.move_examine.executer;

import com.qiu.move_examine.contract.MessageContract;
import com.qiu.move_examine.repertory.db.bean.MessageInfo;

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
    public void loadMessage() {

    }

    @Override
    public void loadDeleteNotice() {

    }
}
