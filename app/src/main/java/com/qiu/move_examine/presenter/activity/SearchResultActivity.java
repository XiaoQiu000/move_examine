package com.qiu.move_examine.presenter.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.qiu.move_examine.R;
import com.qiu.move_examine.common.base.BaseActivity;
import com.qiu.move_examine.common.bean.CarBean;
import com.qiu.move_examine.common.bean.PersonBean;
import com.qiu.move_examine.common.bean.ThingsBean;
import com.qiu.move_examine.contract.TargetContract;
import com.qiu.move_examine.executer.TargetWorker;
import com.qiu.move_examine.presenter.adapter.TargetAdapter;
import com.qiu.move_examine.repertory.webservice.response.QueryResponse;
import com.satsoftec.frame.executer.BaseExecuter;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

public class SearchResultActivity extends BaseActivity<TargetContract.TargetExecute> implements TargetContract.TargetPresenter {
    private static final String TAG = "SearchResultActivity";
    private String condition;
    private SwipeMenuRecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private TargetAdapter adapter;
    private int page = 1;
    private final int PAGE_SIZE = 10;

    @Override
    public TargetContract.TargetExecute initExecutor() {
        return new TargetWorker(this);
    }

    @Override
    protected void init() {
        setContent(R.layout.activity_search_result);
        condition = getIntent().getStringExtra("condition");
        adapter = new TargetAdapter(mContext);
    }

    @Override
    protected void initView() {
        setTitle("搜索结果");
        recyclerView = findViewById(R.id.recycleView);
        refreshLayout = findViewById(R.id.refresh_swip);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        recyclerView.setAdapter(adapter);
        recyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                String id = adapter.getItems().get(position).getId();
                Intent intent = new Intent(mContext, MessageDetailsActivity.class);
                intent.putExtra("mId", id);
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
        showLoading("正在搜索", new ProgressInterruptListener() {
            @Override
            public void onProgressInterruptListener(ProgressDialog progressDialog) {
                hideLoading();
            }
        });
        recyclerView.loadMoreFinish(false, true);
        executor.loadTargetList(condition, page, PAGE_SIZE);
    }

    @Override
    public void targetListResult(boolean isok, String msg, QueryResponse res) {
        if (!isok) {
            mContext.showTip(msg);
            hideLoading();
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
            }
        } else {
            mContext.showTip(res.getError().getMessage());
        }
        if (adapter.getItemCount() == 0) {
            findViewById(R.id.no_view).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.no_view).setVisibility(View.GONE);
        }
        hideLoading();
    }
}
