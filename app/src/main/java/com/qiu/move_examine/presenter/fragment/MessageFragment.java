package com.qiu.move_examine.presenter.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qiu.move_examine.R;
import com.qiu.move_examine.common.ClientConstant;
import com.qiu.move_examine.common.base.BaseFragment;
import com.qiu.move_examine.contract.MessageContract;
import com.qiu.move_examine.executer.MessageWorker;
import com.qiu.move_examine.presenter.adapter.MessageAdapter;
import com.qiu.move_examine.repertory.db.bean.MessageInfo;
import com.satsoftec.frame.executer.BaseExecuter;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

import static com.satsoftec.frame.SFrame.getGson;

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
    }

    @Override
    protected void initView() {
        recyclerView = (SwipeMenuRecyclerView) findView(R.id.recycleView);
        refreshLayout = (SwipeRefreshLayout) findView(R.id.refresh_swip);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {

            }
        });
    }

    @Override
    protected void loadData() {
        adapter.addItem(new MessageAdapter.MessageBean());
        adapter.addItem(new MessageAdapter.MessageBean());
        adapter.addItem(new MessageAdapter.MessageBean());
        adapter.addItem(new MessageAdapter.MessageBean());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void messageResult(List<MessageInfo> list) {

    }

    @Override
    public void deleteNoticeResult() {

    }
}
