package com.qiu.move_examine.presenter.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.qiu.move_examine.R;
import com.qiu.move_examine.common.base.BaseFragment;
import com.qiu.move_examine.presenter.activity.ApplyAlarmActivity;
import com.qiu.move_examine.presenter.activity.ApplyHeadActivity;
import com.qiu.move_examine.presenter.activity.ApplyIdentityActivity;
import com.satsoftec.frame.executer.BaseExecuter;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author Mr.Qiu
 */
public class ApplyFragment extends BaseFragment implements View.OnClickListener {
    private RelativeLayout layout_head, layout_identity, layout_alarm;

    public ApplyFragment() {
    }

    @Override
    protected int getContentRes() {
        return R.layout.fragment_apply;
    }

    @Override
    public BaseExecuter initExecutor() {
        return null;
    }

    @Override
    protected void initView() {
        layout_head = (RelativeLayout) findView(R.id.layout_head);
        layout_identity = (RelativeLayout) findView(R.id.layout_identity);
        layout_alarm = (RelativeLayout) findView(R.id.layout_alarm);
        layout_head.setOnClickListener(this);
        layout_identity.setOnClickListener(this);
        layout_alarm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_head:
                startActivity(new Intent(mContext, ApplyHeadActivity.class));
                break;
            case R.id.layout_identity:
                startActivity(new Intent(mContext, ApplyIdentityActivity.class));
                break;
            case R.id.layout_alarm:
                startActivity(new Intent(mContext, ApplyAlarmActivity.class));
                break;
            default:
                break;
        }
    }
}
