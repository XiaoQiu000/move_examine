package com.qiu.move_examine.presenter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qiu.move_examine.R;
import com.qiu.move_examine.common.base.BaseActivity;
import com.satsoftec.frame.executer.BaseExecuter;

/**
 * @author Mr.Qiu
 */
public class MainActivity extends BaseActivity {

    @Override
    public BaseExecuter initExecutor() {
        return null;
    }

    @Override
    protected void init() {
        setContent(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        hideTitle();
    }
}
