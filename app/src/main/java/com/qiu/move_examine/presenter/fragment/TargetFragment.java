package com.qiu.move_examine.presenter.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.qiu.move_examine.R;
import com.qiu.move_examine.common.base.BaseFragment;
import com.qiu.move_examine.common.utils.PicassoUtils;
import com.qiu.move_examine.contract.TargetContract;
import com.qiu.move_examine.executer.TargetWorker;
import com.qiu.move_examine.presenter.activity.MessageDetailsActivity;
import com.qiu.move_examine.presenter.activity.SearchActivity;
import com.qiu.move_examine.presenter.adapter.MessageAdapter;
import com.qiu.move_examine.presenter.adapter.TargetAdapter;
import com.satsoftec.frame.executer.BaseExecuter;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author Mr.Qiu
 */
public class TargetFragment extends BaseFragment<TargetContract.TargetExecute> implements TargetContract.TargetPresenter, View.OnClickListener {
    private SwipeMenuRecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private TargetAdapter adapter;

    private Button search_bt;

    public TargetFragment() {
    }


    @Override
    protected int getContentRes() {
        return R.layout.fragment_target;
    }

    @Override
    public TargetContract.TargetExecute initExecutor() {
        return new TargetWorker(this);
    }

    @Override
    protected void init() {
        adapter = new TargetAdapter(mContext);
    }

    @Override
    protected void initView() {
        View headView = LayoutInflater.from(mContext).inflate(R.layout.head_target, null, false);
        recyclerView = findView(R.id.recycleView);
        refreshLayout = findView(R.id.refresh_swip);

        search_bt = headView.findViewById(R.id.search_bt);
        search_bt.setOnClickListener(this);
        recyclerView.addHeaderView(headView);

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
                Intent intent = new Intent(mContext, MessageDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void loadData() {
        adapter.addItem(new TargetAdapter.TargetBean());
        adapter.addItem(new TargetAdapter.TargetBean());
        adapter.addItem(new TargetAdapter.TargetBean());
        adapter.addItem(new TargetAdapter.TargetBean());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_bt:
                Intent intent = new Intent(mContext, SearchActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
