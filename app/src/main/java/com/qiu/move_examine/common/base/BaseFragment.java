package com.qiu.move_examine.common.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.satsoftec.frame.executer.BaseExecuter;

/**
 * @author liuyixian
 * @date 16/11/23
 */
public abstract class BaseFragment<T extends BaseExecuter> extends Fragment {
    protected abstract int getContentRes();

    protected BaseActivity mContext;

    protected View rootView;

    private boolean show;
    protected T executor;

    public abstract T initExecutor();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume
            show = true;
        } else {
            //相当于Fragment的onPause
            show = false;
        }
    }

    public boolean isShow() {
        return show;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = getActivity();
        if (activity instanceof BaseActivity) {
            mContext = (BaseActivity) activity;
        }
        executor = initExecutor();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        init();
        return inflater.inflate(getContentRes(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        rootView = view;
        initView();
        loadData();
    }


    protected <T extends View> T findView(@IdRes int r) {
        return (T) rootView.findViewById(r);
    }

    protected void init() {
    }

    protected void initView() {
    }

    protected void loadData() {
    }


    protected void showLoading(final String msg, BaseActivity.ProgressInterruptListener progressInterruptListener) {
        mContext.showLoading(msg, progressInterruptListener);
    }

    protected void hideLoading() {
        mContext.hideLoading();
    }

    protected ApplicationEx getApplicationEx() {
        return (ApplicationEx) mContext.getApplication();
    }

}