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
import com.qiu.move_examine.common.AppContext;
import com.qiu.move_examine.common.ClientConstant;
import com.qiu.move_examine.common.base.BaseFragment;
import com.qiu.move_examine.common.bean.CarBean;
import com.qiu.move_examine.common.bean.PersonBean;
import com.qiu.move_examine.common.bean.ThingsBean;
import com.qiu.move_examine.common.bean.UserInfoBean;
import com.qiu.move_examine.common.utils.PicassoUtils;
import com.qiu.move_examine.contract.TargetContract;
import com.qiu.move_examine.executer.TargetWorker;
import com.qiu.move_examine.presenter.activity.MainActivity;
import com.qiu.move_examine.presenter.activity.MessageDetailsActivity;
import com.qiu.move_examine.presenter.activity.SearchActivity;
import com.qiu.move_examine.presenter.adapter.MessageAdapter;
import com.qiu.move_examine.presenter.adapter.TargetAdapter;
import com.qiu.move_examine.repertory.webservice.response.QueryResponse;
import com.satsoftec.frame.executer.BaseExecuter;
import com.satsoftec.frame.util.SharedPreferenceUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

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

    private int page = 1;
    private final int PAGE_SIZE = 10;

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

        recyclerView.setAdapter(adapter);
        recyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent intent = new Intent(mContext, MessageDetailsActivity.class);
                startActivity(intent);
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                loadData();
            }
        });
        recyclerView.setLoadMoreListener(new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                loadData();
            }
        });
        recyclerView.loadMoreFinish(false, true);
    }

    @Override
    protected void loadData() {
        recyclerView.loadMoreFinish(false, true);
        executor.loadTargetList(page, PAGE_SIZE);
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

    @Override
    public void targetListResult(boolean isok, String msg, QueryResponse res) {
        if (!isok) {
            mContext.showTip(msg);
            return;
        }
        if (page == 1) {
            adapter.clear();
            refreshLayout.setRefreshing(false);
        }
        if (res.getResult() != null) {
            if (res.getResult().getCode().equals("1")) {
                if (res.getResult().getData() == null || res.getResult().getData().size() == 0) {
                    mContext.showTip("没有更多了~");
                    recyclerView.loadMoreFinish(true, false);
                } else {
                    recyclerView.loadMoreFinish(false, true);
                }
                for (int i = 0; i < res.getResult().getData().size(); i++) {
                    TargetAdapter.TargetBean tb = new TargetAdapter.TargetBean();
                    QueryResponse.ResultBean.DataBean dataBean = res.getResult().getData().get(i);
                    List<QueryResponse.ResultBean.DataBean.FieldValuesBean> tempBean = dataBean.getFieldValues();
                    tb.setId(tempBean.get(0).getValue());
                    tb.setMonitorType(tempBean.get(1).getValue());
                    tb.setTargetType(tempBean.get(2).getValue());
                    tb.setCover(tempBean.get(3).getValue());
                    tb.setInvolveCase(tempBean.get(4).getValue());
                    tb.setCharacterDescription(tempBean.get(5).getValue());
                    tb.setPushTime(tempBean.get(6).getValue());
                    switch (tempBean.get(2).getValue()) {
                        case "人":
                            PersonBean personBean = new PersonBean();
                            personBean.setPerName(tempBean.get(7).getValue());
                            personBean.setPerIdNo(tempBean.get(8).getValue());
                            personBean.setPerSex(tempBean.get(9).getValue());
                            personBean.setPerFigure(tempBean.get(10).getValue());
                            tb.setPerson(personBean);
                            break;
                        case "车":
                            CarBean carBean = new CarBean();
                            carBean.setCarNo(tempBean.get(7).getValue());
                            carBean.setBrand(tempBean.get(8).getValue());
                            carBean.setColor(tempBean.get(9).getValue());
                            tb.setCar(carBean);
                            break;
                        case "物":
                            ThingsBean thingsBean = new ThingsBean();
                            thingsBean.setItems(tempBean.get(7).getValue());
                            thingsBean.setShape(tempBean.get(8).getValue());
                            thingsBean.setColor(tempBean.get(9).getValue());
                            tb.setThings(thingsBean);
                            break;
                        default:
                            break;
                    }
                    adapter.addItem(tb);
                }
                adapter.notifyDataSetChanged();
            } else {
                mContext.showTip("数据请求异常，请重试");
                hideLoading();
            }
        } else {
            mContext.showTip(res.getError().getMessage());
        }
    }
}
