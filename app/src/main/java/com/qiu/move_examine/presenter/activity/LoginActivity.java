package com.qiu.move_examine.presenter.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.qiu.move_examine.R;
import com.qiu.move_examine.common.ClientConstant;
import com.qiu.move_examine.common.base.BaseActivity;
import com.qiu.move_examine.common.utils.ActionBarUtils;
import com.qiu.move_examine.contract.LoginContract;
import com.qiu.move_examine.executer.LoginWorker;
import com.qiu.move_examine.repertory.webservice.response.LoginResponse;
import com.satsoftec.frame.executer.BaseExecuter;
import com.satsoftec.frame.util.SharedPreferenceUtil;

/**
 * @author Mr.Qiu
 */
public class LoginActivity extends BaseActivity<LoginContract.LoginExecute> implements LoginContract.LoginPresenter, View.OnFocusChangeListener, View.OnClickListener {

    private EditText et_account, et_password;
    private Button bt_login;

    @Override
    public LoginContract.LoginExecute initExecutor() {
        return new LoginWorker(this);
    }

    @Override
    protected void init() {
        setContent(R.layout.activity_login);
    }

    @Override
    protected void initView() {
        hideTitle();
        findViewById(R.id.base_relayout).setBackgroundResource(R.mipmap.background_login);
        findViewById(R.id.b_content).setBackgroundColor(getResources().getColor(R.color.translate));
        ActionBarUtils.setTransparent(this);

        et_account = findViewById(R.id.et_account);
        et_password = findViewById(R.id.et_password);
        bt_login = findViewById(R.id.bt_login);

        bt_login.setOnClickListener(this);
        et_account.setOnFocusChangeListener(this);
        et_password.setOnFocusChangeListener(this);
        et_account.clearFocus();
        et_password.clearFocus();
    }

    @Override
    protected void loadData() {
        String account = SharedPreferenceUtil.getSharedPreString(ClientConstant.SPREFERENCES_LOGIN_ACCOUNT);
        String password = SharedPreferenceUtil.getSharedPreString(ClientConstant.SPREFERENCES_LOGIN_PASSWORD);
        if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(password)) {
            et_account.setText(account);
            et_password.setText(password);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
//                toLogin();
                startActivity(new Intent(mContext, MainActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * 登录
     */
    private void toLogin() {
        et_account.setError(null);
        et_password.setError(null);

        String account = et_account.getText().toString();
        if (TextUtils.isEmpty(account)) {
            et_account.setError(getString(R.string.error_field_required));
            et_account.requestFocus();
            return;
        }

        String password = et_password.getText().toString();
        if (TextUtils.isEmpty(password)) {
            et_password.setError(getString(R.string.error_invalid_password));
            et_password.requestFocus();
            return;
        }

        showLoading("正在登录...", new ProgressInterruptListener() {
            @Override
            public void onProgressInterruptListener(ProgressDialog progressDialog) {
                hideLoading();
            }
        });
        executor.loginByAccount(account, password);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        EditText et = (EditText) v;
        String hint;

        switch (v.getId()) {
            case R.id.et_account:
            case R.id.et_password:
                if (hasFocus) {
                    hint = et.getHint().toString();
                    et.setTag(hint);
                    et.setHint("");
                } else {
                    hint = et.getTag().toString();
                    et.setHint(hint);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void loginResult(boolean isok, String msg, LoginResponse res) {
        hideLoading();
        if (isok) {
            startActivity(new Intent(mContext, MainActivity.class));
            finish();
        } else {
            showTip(msg);
        }
    }
}
