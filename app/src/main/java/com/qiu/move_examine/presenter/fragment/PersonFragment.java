package com.qiu.move_examine.presenter.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
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
import org.netty.PushClient;
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
                        AppContext.self().logout(mContext);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void loadData() {
        String perName = SharedPreferenceUtil.getSharedPreString(ClientConstant.SPREFERENCES_LOGIN_NAME);
        String isp = SharedPreferenceUtil.getSharedPreString(ClientConstant.SPREFERENCES_LOGIN_INSPECTIONUNIT);
        String cover = SharedPreferenceUtil.getSharedPreString(ClientConstant.SPREFERENCES_LOGIN_COVER);
        tv_inspectionUnit.setText(isp);
        tv_perName.setText(perName);
//        byte [] input = Base64.decode(cover, Base64.DEFAULT);
//        Bitmap bitmap = BitmapFactory.decodeByteArray(input, 0, input.length);
//        iv_head.setImageBitmap(bitmap);
//        PicassoUtils.getinstance().loadImage(mContext, cover, iv_head,
////                R.mipmap.icon_person_head);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_person, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

}
