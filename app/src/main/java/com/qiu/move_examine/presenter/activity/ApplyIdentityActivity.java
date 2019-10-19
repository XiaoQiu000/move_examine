package com.qiu.move_examine.presenter.activity;

import com.qiu.move_examine.R;
import com.qiu.move_examine.common.base.BaseActivity;
import com.satsoftec.frame.executer.BaseExecuter;

/**
 * @author Mr.Qiu
 */
public class ApplyIdentityActivity extends BaseActivity {

    @Override
    public BaseExecuter initExecutor() {
        return null;
    }

    @Override
    protected void init() {
        setContent(R.layout.activity_apply_head);
    }

    @Override
    protected void initView() {
        setTitle("身份确认");
    }
}
