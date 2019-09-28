package com.qiu.move_examine.executer;

import android.util.Log;

import com.qiu.move_examine.common.AppContext;
import com.qiu.move_examine.common.ClientConstant;
import com.qiu.move_examine.contract.MessageContract;
import com.qiu.move_examine.repertory.db.bean.NoticeInfo;
import com.satsoftec.frame.repertory.dbTool.DatabaseManage;
import com.satsoftec.frame.util.SharedPreferenceUtil;

import java.util.List;

/**
 * @author Mr.Qiu
 */
public class MessageWorker implements MessageContract.MessageExecute {
    private static final String TAG = "MessageWorker";
    private MessageContract.MessagePresenter presenter;

    public MessageWorker(MessageContract.MessagePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loadNotice() {
        List<NoticeInfo> list;
        Log.e(TAG, "loadNotice: " + AppContext.self());
        String userId = SharedPreferenceUtil.getSharedPreString(ClientConstant.SPREFERENCES_LOGIN_ID);
        list = DatabaseManage.getList(NoticeInfo.class, "ownerId=" + userId + " order by createDate desc");
        if (list != null) {
            presenter.noticeResult(list);
        }
    }

    @Override
    public void loadDeleteNotice() {
        String userId = SharedPreferenceUtil.getSharedPreString(ClientConstant.SPREFERENCES_LOGIN_ID);
        DatabaseManage.delete(NoticeInfo.class, "ownerId=" + userId);
        presenter.deleteNoticeResult();
    }
}
