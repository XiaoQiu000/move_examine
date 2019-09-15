package com.qiu.move_examine.presenter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;

import com.qiu.move_examine.R;
import com.qiu.move_examine.common.base.BaseActivity;
import com.satsoftec.frame.executer.BaseExecuter;

/**
 * @author Mr.Qiu
 */
public class SearchActivity extends BaseActivity {
    private RadioButton rb_person, rb_car, rb_thing, rb_arrest, rb_intercept, rb_notice;

    @Override
    public BaseExecuter initExecutor() {
        return null;
    }

    @Override
    protected void init() {
        setContent(R.layout.activity_search);
    }

    @Override
    protected void initView() {
        setTitle("高级搜索");
        rb_person = findViewById(R.id.rb_person);
        rb_car = findViewById(R.id.rb_car);
        rb_thing = findViewById(R.id.rb_thing);
        rb_arrest = findViewById(R.id.rb_arrest);
        rb_intercept = findViewById(R.id.rb_intercept);
        rb_notice = findViewById(R.id.rb_notice);
        rb_person.setChecked(true);
        rb_arrest.setChecked(true);
    }
}
