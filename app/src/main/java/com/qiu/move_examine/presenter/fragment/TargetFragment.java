package com.qiu.move_examine.presenter.fragment;


import android.support.v4.app.Fragment;

import com.qiu.move_examine.R;
import com.qiu.move_examine.common.base.BaseFragment;
import com.satsoftec.frame.executer.BaseExecuter;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author Mr.Qiu
 */
public class TargetFragment extends BaseFragment {


    public TargetFragment() {
    }


    @Override
    protected int getContentRes() {
        return R.layout.fragment_target;
    }

    @Override
    public BaseExecuter initExecutor() {
        return null;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView() {

    }
}
