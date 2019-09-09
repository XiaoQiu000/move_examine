package com.qiu.move_examine.presenter.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.qiu.move_examine.R;
import com.qiu.move_examine.common.base.BaseActivity;
import com.qiu.move_examine.common.view.GetTouchRelativeLayout;
import com.qiu.move_examine.presenter.fragment.MessageFragment;
import com.qiu.move_examine.presenter.fragment.PersonFragment;
import com.qiu.move_examine.presenter.fragment.TargetFragment;
import com.satsoftec.frame.executer.BaseExecuter;

/**
 * @author Mr.Qiu
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private RadioButton rb_message, rb_target, rb_person;
    private GetTouchRelativeLayout message_layout, targetLayout, personLayout;
    private MessageFragment messageFragment;
    private TargetFragment targetFragment;
    private PersonFragment personFragment;
    private Fragment preFragment;

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
        messageFragment = new MessageFragment();
        targetFragment = new TargetFragment();
        personFragment = new PersonFragment();

        rb_message = findViewById(R.id.message_rb);
        rb_target = findViewById(R.id.target_rb);
        rb_person = findViewById(R.id.person_rb);
        message_layout = findViewById(R.id.message_layout);
        targetLayout = findViewById(R.id.target_layout);
        personLayout = findViewById(R.id.person_layout);

        rb_message.setOnClickListener(this);
        rb_target.setOnClickListener(this);
        rb_person.setOnClickListener(this);
        message_layout.setOnClickListener(this);
        targetLayout.setOnClickListener(this);
        personLayout.setOnClickListener(this);

        rb_message.setChecked(true);
        rb_target.setChecked(false);
        rb_person.setChecked(false);

        getSupportFragmentManager().beginTransaction().add(R.id.main_frame, messageFragment).commit();
        preFragment = messageFragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message_layout:
                rb_message.setChecked(true);
                rb_target.setChecked(false);
                rb_person.setChecked(false);
                if (messageFragment.isVisible()) {
                    return;
                }
                if (messageFragment != null && messageFragment.isAdded()) {
                    getSupportFragmentManager().beginTransaction().show(messageFragment).hide(preFragment).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().add(R.id.main_frame, messageFragment).hide(preFragment).commit();
                }
                preFragment = messageFragment;
                break;
            case R.id.target_layout:
                rb_message.setChecked(false);
                rb_target.setChecked(true);
                rb_person.setChecked(false);
                if (targetFragment.isVisible()) {
                    return;
                }
                if (targetFragment != null && targetFragment.isAdded()) {
                    getSupportFragmentManager().beginTransaction().show(targetFragment).hide(preFragment).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().add(R.id.main_frame, targetFragment).hide(preFragment).commit();
                }
                preFragment = targetFragment;
                break;

            case R.id.person_layout:
                rb_message.setChecked(false);
                rb_target.setChecked(false);
                rb_person.setChecked(true);
                if (personFragment.isVisible()) {
                    return;
                }
                if (personFragment != null && personFragment.isAdded()) {
                    getSupportFragmentManager().beginTransaction().show(personFragment).hide(preFragment).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().add(R.id.main_frame, personFragment).hide(preFragment).commit();
                }
                preFragment = personFragment;
                break;
            default:
                break;
        }
    }


}
