package com.qiu.move_examine.presenter.fragment;


import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiu.move_examine.R;
import com.qiu.move_examine.common.base.BaseFragment;
import com.satsoftec.frame.executer.BaseExecuter;

/**
 * A simple {@link Fragment} subclass.
 * 个人中心
 *
 * @author Mr.Qiu
 */
public class PersonFragment extends BaseFragment {
    private ImageView iv_head;
    private TextView tv_inspectionUnit, tv_perName;

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
        iv_head = (ImageView) findView(R.id.iv_head);
        tv_inspectionUnit = (TextView) findView(R.id.tv_inspectionUnit);
        tv_perName = (TextView) findView(R.id.tv_perName);
    }

    @Override
    protected void loadData() {

    }
}
