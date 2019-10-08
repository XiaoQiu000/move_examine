package com.qiu.move_examine.presenter.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.reflect.TypeToken;
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
import com.qiu.move_examine.repertory.db.bean.JsonBeanInfo;
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
    private EditText search_et;
    private ImageView search_img;
    private String keyWord = "";

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
        search_et = headView.findViewById(R.id.search_et);
        search_img = headView.findViewById(R.id.search_img);
        search_bt.setOnClickListener(this);
        recyclerView.addHeaderView(headView);
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
                loadInternetData();
            }
        });
        recyclerView.setLoadMoreListener(new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                loadInternetData();
            }
        });
        recyclerView.loadMoreFinish(false, true);

        search_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()==0){
                    search_img.setVisibility(View.VISIBLE);
                }else {
                    search_img.setVisibility(View.INVISIBLE);
                }
                if (mHandler.hasMessages(1)) {
                    mHandler.removeMessages(1);
                }
                keyWord = s.toString();
                mHandler.sendEmptyMessageDelayed(1, 1000);
            }
        });
        search_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mContext.getWindow().getDecorView().getWindowToken(), 0);
                }
            }
        });
    }

    @Override
    protected void loadData() {
        recyclerView.loadMoreFinish(false, true);
        loadLocalData();
        loadInternetData();
    }

    private void loadLocalData() {
        //加载本地数据
        Long userId = Long.parseLong(SharedPreferenceUtil.getSharedPreString(ClientConstant.SPREFERENCES_LOGIN_ID));

        List<QueryResponse.ResultBean.DataBean> orderInfosList = JsonBeanInfo.getBean(userId, userId, JsonBeanInfo.Type.TARGET_LIST,
                new TypeToken<List<QueryResponse.ResultBean.DataBean>>() {
                }.getType());
        if (orderInfosList != null) {
            toLoadList(orderInfosList);
        }
    }

    private void loadInternetData() {
        String condition = "";
        if (!TextUtils.isEmpty(keyWord)) {
            condition = "(involve_case like '%" + keyWord + "%' or character_description like '%" + keyWord +
                    "%' or brand like '%" + keyWord + "%' or items like '%" + keyWord + "%' or per_name like '%" + keyWord
                    + "%')";
        }

        executor.loadTargetList(condition, page, PAGE_SIZE);
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
                List<QueryResponse.ResultBean.DataBean> data = res.getResult().getData();
                if (page == 1) {
                    Long userId = Long.parseLong(SharedPreferenceUtil.getSharedPreString(ClientConstant.SPREFERENCES_LOGIN_ID));
                    JsonBeanInfo.saveBean(userId, userId, JsonBeanInfo.Type.TARGET_LIST,
                            data);
                }

                toLoadList(data);

            } else if (res.getResult().getCode().equals("2")) {
                mContext.showTip("会话超时");
                AppContext.self().logout(mContext);
            } else {
                mContext.showTip("数据请求异常，请重试");
                hideLoading();
            }
        } else {
            mContext.showTip(res.getError().getMessage());
        }
        if (adapter.getItemCount() == 0) {
            findView(R.id.no_view).setVisibility(View.VISIBLE);
        } else {
            findView(R.id.no_view).setVisibility(View.GONE);
        }
    }

    private void toLoadList(List<QueryResponse.ResultBean.DataBean> data) {
        for (int i = 0; i < data.size(); i++) {
            TargetAdapter.TargetBean tb = new TargetAdapter.TargetBean();
            QueryResponse.ResultBean.DataBean dataBean = data.get(i);
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
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                loadInternetData();
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        hideInput();
    }

    private void hideInput() {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mContext.getWindow().getDecorView().getWindowToken(), 0);

        findView(R.id.title).setFocusableInTouchMode(true);
        findView(R.id.title).requestFocus();
    }
}
