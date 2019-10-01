package com.qiu.move_examine.presenter.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.Person;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.qiu.move_examine.R;
import com.qiu.move_examine.common.AppContext;
import com.qiu.move_examine.common.ClientConstant;
import com.qiu.move_examine.common.base.BaseFragment;
import com.qiu.move_examine.common.bean.CarBean;
import com.qiu.move_examine.common.bean.PersonBean;
import com.qiu.move_examine.common.bean.ThingsBean;
import com.qiu.move_examine.contract.MessageContract;
import com.qiu.move_examine.executer.MessageWorker;
import com.qiu.move_examine.presenter.activity.MessageDetailsActivity;
import com.qiu.move_examine.presenter.adapter.MessageAdapter;
import com.qiu.move_examine.presenter.event.NoticeEvent;
import com.qiu.move_examine.repertory.db.bean.NoticeInfo;
import com.satsoftec.frame.SFrame;
import com.satsoftec.frame.repertory.dbTool.DatabaseManage;
import com.satsoftec.frame.util.SharedPreferenceUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 * 我的消息
 *
 * @author Mr.Qiu
 */
public class MessageFragment extends BaseFragment<MessageContract.MessageExecute> implements MessageContract.MessagePresenter {
    private SwipeMenuRecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private MessageAdapter adapter;

    public MessageFragment() {
    }


    @Override
    protected int getContentRes() {
        return R.layout.fragment_message;
    }

    @Override
    public MessageContract.MessageExecute initExecutor() {
        return new MessageWorker(this);
    }

    @Override
    protected void init() {
        adapter = new MessageAdapter(mContext);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {
        recyclerView = findView(R.id.recycleView);
        refreshLayout = findView(R.id.refresh_swip);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                String userId = SharedPreferenceUtil.getSharedPreString(ClientConstant.SPREFERENCES_LOGIN_ID);
                MessageAdapter.MessageBean messageBean = adapter.getItems().get(position);
                NoticeInfo noticeInfo = DatabaseManage.getBean(NoticeInfo.class, "ownerId = '" + userId + "' and noticeId = " + messageBean.getId());
                noticeInfo.setNoticeHaveRead(true);
                DatabaseManage.update(noticeInfo, "ownerId = '" + userId + "' and noticeId=" + messageBean.getId());
                adapter.getItems().get(position).setRead(true);
                adapter.notifyItemChanged(position);
                Intent intent = new Intent(mContext, MessageDetailsActivity.class);
                intent.putExtra("mId", messageBean.getId() + "");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void loadData() {
        executor.loadNotice();
    }

    @Override
    public void noticeResult(List<NoticeInfo> list) {
        adapter.clear();
        for (int i = 0; i < list.size(); i++) {
            NoticeInfo noticeInfo = list.get(i);
            MessageAdapter.MessageBean mBean = new MessageAdapter.MessageBean();
            mBean.setId(noticeInfo.getNoticeId());
            mBean.setTargetType(noticeInfo.getTargetType());
            mBean.setMonitorType(noticeInfo.getMonitorType());
            mBean.setCover(noticeInfo.getCover());
            mBean.setPushTime(noticeInfo.getPushTime());
            mBean.setPerson(SFrame.getGson().fromJson(noticeInfo.getPerson(), PersonBean.class));
            mBean.setCar(SFrame.getGson().fromJson(noticeInfo.getCar(), CarBean.class));
            mBean.setThings(SFrame.getGson().fromJson(noticeInfo.getThings(), ThingsBean.class));
            mBean.setRead(noticeInfo.getNoticeHaveRead());
            adapter.addItem(mBean);
        }
        adapter.notifyDataSetChanged();
        refreshLayout.setRefreshing(false);
        if (adapter.getItemCount() == 0) {
            findView(R.id.no_view).setVisibility(View.VISIBLE);
        } else {
            findView(R.id.no_view).setVisibility(View.GONE);
        }
    }

    @Override
    public void deleteNoticeResult() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(NoticeEvent event) {
        loadData();
    }

}
