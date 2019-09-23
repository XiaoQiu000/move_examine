package com.qiu.move_examine.presenter.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiu.move_examine.R;
import com.qiu.move_examine.common.AppContext;
import com.qiu.move_examine.common.ClientConstant;
import com.qiu.move_examine.common.base.BaseFragment;
import com.qiu.move_examine.common.bean.UserInfoBean;
import com.qiu.move_examine.common.utils.PicassoUtils;
import com.qiu.move_examine.netty.PushClient;
import com.qiu.move_examine.presenter.activity.LoginActivity;
import com.satsoftec.frame.executer.BaseExecuter;
import com.satsoftec.frame.util.SharedPreferenceUtil;

/**
 * A simple {@link Fragment} subclass.
 * 个人中心
 *
 * @author Mr.Qiu
 */
public class PersonFragment extends BaseFragment {
    private ImageView iv_head;
    private TextView tv_inspectionUnit, tv_perName;
    private Toolbar toolbar;

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
        setHasOptionsMenu(true);
    }

    @Override
    protected void initView() {
        iv_head = (ImageView) findView(R.id.iv_head);
        tv_inspectionUnit = (TextView) findView(R.id.tv_inspectionUnit);
        tv_perName = (TextView) findView(R.id.tv_perName);
        toolbar = (Toolbar) findView(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_person);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.exitLogin:
                        logout();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void loadData() {
        UserInfoBean userInfo = AppContext.self().getUserInfo();
        tv_inspectionUnit.setText(TextUtils.isEmpty(userInfo.getInspectionUnit()) ? "" : userInfo.getInspectionUnit());
        tv_perName.setText(TextUtils.isEmpty(userInfo.getPerName()) ? "" : userInfo.getPerName());
        PicassoUtils.getinstance().loadImage(mContext, userInfo.getPerIcon(), iv_head,
                R.mipmap.icon_person_head);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_person, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    /**
     * 退出登录
     */
    public void logout() {
        SharedPreferenceUtil.saveSharedPreBoolean(ClientConstant.SPREFERENCES_LOGIN_EXIT, false);
        getApplicationEx().closeAllActivity();
        AppContext.self().setUserInfo(null);
        //关闭长连接
        PushClient.close();
        //开启登录界面
        Intent intent = new Intent(mContext, LoginActivity.class);
        intent.putExtra(ClientConstant.SPREFERENCES_LOGIN_EXIT, true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
