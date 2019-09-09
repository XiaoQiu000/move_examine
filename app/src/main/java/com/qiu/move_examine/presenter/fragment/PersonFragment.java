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
public class PersonFragment extends BaseFragment {


    public PersonFragment() {
    }


    @Override
    protected int getContentRes() {
        return R.layout.fragment_person;
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
