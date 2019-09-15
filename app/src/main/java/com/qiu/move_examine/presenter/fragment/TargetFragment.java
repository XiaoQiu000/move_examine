package com.qiu.move_examine.presenter.fragment;


import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.qiu.move_examine.R;
import com.qiu.move_examine.common.base.BaseFragment;
import com.satsoftec.frame.executer.BaseExecuter;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author Mr.Qiu
 */
public class TargetFragment extends BaseFragment {
    private ImageView imageview;

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
        imageview = (ImageView) findView(R.id.imageview);
    }

    @Override
    protected void loadData() {
    }
}
