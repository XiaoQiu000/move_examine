package com.qiu.move_examine.presenter.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qiu.move_examine.R;
import com.qiu.move_examine.common.base.BaseFragment;
import com.satsoftec.frame.executer.BaseExecuter;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author Mr.Qiu
 */
public class MessageFragment extends BaseFragment {


    public MessageFragment() {
    }


    @Override
    protected int getContentRes() {
        return R.layout.fragment_message;
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
