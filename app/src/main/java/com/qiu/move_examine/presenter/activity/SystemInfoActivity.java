package com.qiu.move_examine.presenter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qiu.move_examine.R;
import com.qiu.move_examine.common.base.BaseActivity;
import com.satsoftec.frame.executer.BaseExecuter;

public class SystemInfoActivity extends BaseActivity {

    @Override
    public BaseExecuter initExecutor() {
        return null;
    }


    @Override
    protected void init() {
        setContent(R.layout.activity_system_info);
    }

    @Override
    protected void initView() {
        setTitle("系统信息");
    }
}
