package com.qiu.move_examine.presenter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.qiu.move_examine.R;
import com.qiu.move_examine.common.base.BaseActivity;
import com.satsoftec.frame.executer.BaseExecuter;

/**
 * @author Mr.Qiu
 */
public class SearchActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private RadioGroup target_radioGroup;
    private RadioButton rb_person, rb_car, rb_thing, rb_arrest, rb_intercept, rb_notice;
    private LinearLayout layout_type_person, layout_type_car, layout_type_thing;

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
        target_radioGroup = findViewById(R.id.target_radioGroup);
        rb_person = findViewById(R.id.rb_person);
        rb_car = findViewById(R.id.rb_car);
        rb_thing = findViewById(R.id.rb_thing);
        rb_arrest = findViewById(R.id.rb_arrest);
        rb_intercept = findViewById(R.id.rb_intercept);
        rb_notice = findViewById(R.id.rb_notice);
        layout_type_person = findViewById(R.id.layout_type_person);
        layout_type_car = findViewById(R.id.layout_type_car);
        layout_type_thing = findViewById(R.id.layout_type_thing);

        target_radioGroup.setOnCheckedChangeListener(this);
        rb_person.setChecked(true);
        rb_arrest.setChecked(true);

        findViewById(R.id.bt_search).setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_person:
                layout_type_person.setVisibility(View.VISIBLE);
                layout_type_car.setVisibility(View.GONE);
                layout_type_thing.setVisibility(View.GONE);
                break;
            case R.id.rb_car:
                layout_type_person.setVisibility(View.GONE);
                layout_type_car.setVisibility(View.VISIBLE);
                layout_type_thing.setVisibility(View.GONE);
                break;
            case R.id.rb_thing:
                layout_type_person.setVisibility(View.GONE);
                layout_type_car.setVisibility(View.GONE);
                layout_type_thing.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_search:
                Intent intent = new Intent(mContext, MessageDetailsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
