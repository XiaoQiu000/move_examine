package com.qiu.move_examine.presenter.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qiu.move_examine.R;
import com.qiu.move_examine.common.AppContext;
import com.qiu.move_examine.common.ClientConstant;
import com.qiu.move_examine.common.base.BaseActivity;
import com.qiu.move_examine.common.bean.UserInfoBean;
import com.qiu.move_examine.common.utils.ActionBarUtils;
import com.qiu.move_examine.contract.LoginContract;
import com.qiu.move_examine.executer.LoginWorker;
import org.netty.PushClient;

import com.qiu.move_examine.repertory.webservice.response.ConnectResponse;
import com.qiu.move_examine.repertory.webservice.response.QueryResponse;
import com.satsoftec.frame.util.SharedPreferenceUtil;

import java.util.List;

/**
 * @author Mr.Qiu
 */
public class LoginActivity extends BaseActivity<LoginContract.LoginExecute> implements LoginContract.LoginPresenter, View.OnFocusChangeListener, View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private EditText et_account, et_password;
    private Button bt_login;
    private boolean fromExtra = false;//是否退出登录过来的

    @Override
    public LoginContract.LoginExecute initExecutor() {
        return new LoginWorker(this);
    }

    @Override
    protected void init() {
        setContent(R.layout.activity_login);
        fromExtra = getIntent().getBooleanExtra(ClientConstant.SPREFERENCES_LOGIN_EXIT, false);

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
        bt_login.setEnabled(false);
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (fromExtra) {
                    findViewById(R.id.welcomeTv).setVisibility(View.GONE);
                    findViewById(R.id.login_input).setVisibility(View.VISIBLE);
                } else {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //如果已经退出登录过
                            boolean isExit = SharedPreferenceUtil.getSharedPreBoolean(ClientConstant.SPREFERENCES_LOGIN_EXIT);
                            if (isExit) {
                                toLogin(false);
                            } else {
                                findViewById(R.id.welcomeTv).setVisibility(View.GONE);
                                findViewById(R.id.login_input).setVisibility(View.VISIBLE);
                                bt_login.setEnabled(true);
                            }
                        }
                    });
                }
            }
        }).start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                toLogin(true);
                break;
            default:
                break;
        }
    }

    /**
     * 登录
     */
    private void toLogin(boolean isShowDialog) {
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
        if (isShowDialog) {
            showLoading("正在登录...", new ProgressInterruptListener() {
                @Override
                public void onProgressInterruptListener(ProgressDialog progressDialog) {
                    hideLoading();
                }
            });
            executor.connect(account, password);
        } else {
            executor.loginByAccount(account, password);
        }
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
    public void connectResult(boolean isok, String msg, ConnectResponse res, String account, String password) {
        if (!isok) {
            showTip(msg);
            hideLoading();
            return;
        }
        if (res.getResult() != null) {
            if (res.getResult().getCode().equals("1")) {
                ClientConstant.sessionId = res.getResult().getData().getSessionId();
                executor.loginByAccount(account, password);
            } else {
                showTip("连接异常，请重试");
                hideLoading();
            }
        } else {
            showTip(res.getError().getMessage());
            hideLoading();
        }
    }

    @Override
    public void loginResult(boolean isok, String msg, QueryResponse res, String account, String password) {
        if (!isok) {
            findViewById(R.id.welcomeTv).setVisibility(View.GONE);
            findViewById(R.id.login_input).setVisibility(View.VISIBLE);
            bt_login.setEnabled(true);
            showTip(msg);
            hideLoading();
            return;
        }
        if (res.getResult() != null) {
            if (res.getResult().getCode().equals("1")) {

                List<QueryResponse.ResultBean.DataBean.FieldValuesBean> fieldValues = res.getResult().getData().get(0).getFieldValues();
                SharedPreferenceUtil.saveSharedPreBoolean(ClientConstant.SPREFERENCES_LOGIN_EXIT, true);
                SharedPreferenceUtil.saveSharedPreString(ClientConstant.SPREFERENCES_LOGIN_ID, fieldValues.get(0).getValue());
                SharedPreferenceUtil.saveSharedPreString(ClientConstant.SPREFERENCES_LOGIN_NAME, fieldValues.get(1).getValue());
                SharedPreferenceUtil.saveSharedPreString(ClientConstant.SPREFERENCES_LOGIN_INSPECTIONUNIT, fieldValues.get(3).getValue());
                SharedPreferenceUtil.saveSharedPreString(ClientConstant.SPREFERENCES_LOGIN_COVER, fieldValues.get(2).getValue());
                SharedPreferenceUtil.saveSharedPreString(ClientConstant.SPREFERENCES_LOGIN_ACCOUNT, account);
                SharedPreferenceUtil.saveSharedPreString(ClientConstant.SPREFERENCES_LOGIN_PASSWORD, password);
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
            } else {
                showTip("登录异常，请重试");
                hideLoading();
            }
        } else {
            findViewById(R.id.welcomeTv).setVisibility(View.GONE);
            findViewById(R.id.login_input).setVisibility(View.VISIBLE);
            bt_login.setEnabled(true);
            showTip(res.getError().getMessage());
            hideLoading();
        }
    }

}
