package com.qiu.move_examine.common.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qiu.move_examine.R;
import com.satsoftec.frame.executer.BaseExecuter;
import com.satsoftec.frame.util.CommonUtil;

/**
 * @author Mr.Qiu
 */
public abstract class BaseActivity<T extends BaseExecuter> extends AppCompatActivity {
    protected BaseActivity mContext;

    protected ProgressDialog progressDialog;
    private boolean loadViewed = false;
    protected Toolbar toolbar;
    private int conRes = 0;
    private RelativeLayout content;

    private LinearLayout ll_menu;

    public RelativeLayout getContent() {
        return content;
    }

    protected T executor;

    public abstract T initExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        executor = initExecutor();
        getApplicationEx().addActivities(this);
        init();

        super.setContentView(R.layout.activity_base);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ll_menu = (LinearLayout) findViewById(R.id.ll_menu);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.mipmap.base_return);

        content = (RelativeLayout) findViewById(R.id.b_content);
        View view = LayoutInflater.from(this).inflate(conRes, null);
        content.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        loadViewed = true;
        initView();
        loadData();
    }

    protected void setContent(@LayoutRes int resId) {
        if (loadViewed) {
            throw new RuntimeException("内容页必须在init里面设置");
        }
        conRes = resId;
    }

    protected void hideTitle() {
        toolbar.setVisibility(View.GONE);
        findViewById(R.id.base_line).setVisibility(View.GONE);
    }

    protected void showTitle() {
        toolbar.setVisibility(View.VISIBLE);
    }

    /**
     * 设置toolbar居中标题
     * 返回样式为×号
     *
     * @param title
     */
    public void setTitle(String title) {
        if (CommonUtil.isNull(title)) {
            return;
        }
        TextView toolbarTitle = (TextView) findViewById(R.id.title);
        toolbarTitle.setText(title);

        toolbar.setBackgroundColor(getResources().getColor(android.R.color.white));
    }

    protected void init() {
    }

    protected void initView() {
    }

    protected void loadData() {
    }

    private final Integer progressDialogMark = 1;

    /**
     * 展示加载视图
     *
     * @param msg
     * @param progressInterruptListener
     */
    public void showLoading(final String msg, @Nullable final ProgressInterruptListener progressInterruptListener) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                synchronized (progressDialogMark) {
                    if (!isFinishing()) {
                        if (progressDialog == null) {
                            progressDialog = new ProgressDialog(BaseActivity.this);
                            progressDialog.setMessage(msg);
                            progressDialog.show();
                            if (progressInterruptListener != null) {
                                progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                    @Override
                                    public void onCancel(DialogInterface dialogInterface) {
                                        progressInterruptListener.onProgressInterruptListener(BaseActivity.this.progressDialog);
                                    }
                                });
                            }
                        } else {
                            progressDialog.setMessage(msg);
                        }
                    }
                }
            }
        });
    }

    public interface ProgressInterruptListener {
        /**
         * 关闭进度窗口回掉
         *
         * @param progressDialog
         */
        void onProgressInterruptListener(ProgressDialog progressDialog);
    }

    public synchronized void hideLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                synchronized (progressDialogMark) {
                    if (progressDialog != null) {
                        progressDialog.dismiss();
                        progressDialog = null;
                    }
                }
            }
        });
    }

    public void addMenu(View view) {
        if (view != null) {
            int h = ll_menu.getLayoutParams().height;
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(h, h);
            view.setLayoutParams(lp);
            ll_menu.addView(view);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        getApplicationEx().removeActivities(this);
    }

    protected ApplicationEx getApplicationEx() {
        return (ApplicationEx) getApplication();
    }

    private int statusBarHeight = 0;

    protected int getStatusBarHeight() {
        if (statusBarHeight == 0) {
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                statusBarHeight = getResources().getDimensionPixelSize(resourceId);
            }
        }
        return statusBarHeight;
    }

}



