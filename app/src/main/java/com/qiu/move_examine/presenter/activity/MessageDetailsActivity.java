package com.qiu.move_examine.presenter.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiu.move_examine.R;
import com.qiu.move_examine.common.AppContext;
import com.qiu.move_examine.common.ClientConstant;
import com.qiu.move_examine.common.base.BaseActivity;
import com.qiu.move_examine.common.bean.CarBean;
import com.qiu.move_examine.common.bean.PersonBean;
import com.qiu.move_examine.common.bean.ThingsBean;
import com.qiu.move_examine.common.utils.PicassoUtils;
import com.qiu.move_examine.contract.DetailsContract;
import com.qiu.move_examine.executer.DetailsWorker;
import com.qiu.move_examine.repertory.webservice.response.QueryResponse;
import com.satsoftec.frame.executer.BaseExecuter;

import java.util.List;

/**
 * @author Mr.Qiu
 */
public class MessageDetailsActivity extends BaseActivity<DetailsContract.DetailsExecute> implements DetailsContract.DetailsPresenter {
    private String mId;
    private ImageView coverIv;
    private TextView nameTv, stateTv, sexTv, featureTv, perNumTv, involveCaseTv, descriptionTv;
    private TextView unitTv, unitPersonTv, unitPhoneTv;

    @Override
    public DetailsContract.DetailsExecute initExecutor() {
        return new DetailsWorker(this);
    }

    @Override
    protected void init() {
        setContent(R.layout.activity_message_details);
        mId = getIntent().getStringExtra("mId");

        if (TextUtils.isEmpty(mId)) {
            showTip("发生错误");
            finish();
            return;
        }
    }

    @Override
    protected void initView() {
        setTitle("详情");
        coverIv = findViewById(R.id.coverIv);
        nameTv = findViewById(R.id.nameTv);
        stateTv = findViewById(R.id.stateTv);
        sexTv = findViewById(R.id.sexTv);
        featureTv = findViewById(R.id.featureTv);
        perNumTv = findViewById(R.id.perNumTv);
        involveCaseTv = findViewById(R.id.involveCaseTv);
        descriptionTv = findViewById(R.id.descriptionTv);
        unitTv = findViewById(R.id.unitTv);
        unitPersonTv = findViewById(R.id.unitPersonTv);
        unitPhoneTv = findViewById(R.id.unitPhoneTv);
    }

    @Override
    protected void loadData() {
        hasReceive = false;
        executor.loadDetails(mId);
        uiHandle.sendEmptyMessageDelayed(1, 5000);
    }

    private boolean hasReceive = false;
    private Handler uiHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //实现计时器功能
                case 1:
                    if (!hasReceive){
                        loadData();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void detailsResult(boolean isok, String msg, QueryResponse res) {
        hasReceive = true;
        if (!isok) {
            showTip(msg);
            hideLoading();
            return;
        }
        if (res.getResult() != null) {
            if (res.getResult().getCode().equals("1")) {
                List<QueryResponse.ResultBean.DataBean.FieldValuesBean> fieldValues = res.getResult().getData().get(0).getFieldValues();
                involveCaseTv.setText("涉及案件：" + fieldValues.get(4).getValue());
                descriptionTv.setText("异常特征描述：" + fieldValues.get(5).getValue());
                stateTv.setText(fieldValues.get(1).getValue());
                switch (fieldValues.get(1).getValue()) {
                    case "抓捕":
                        stateTv.setBackgroundResource(R.drawable.shape_text_arrest);
                        break;
                    case "拦截":
                        stateTv.setBackgroundResource(R.drawable.shape_text_intercept);
                        break;
                    case "通知":
                        stateTv.setBackgroundResource(R.drawable.shape_text_notice);
                        break;
                    default:
                        break;
                }
                switch (fieldValues.get(2).getValue()) {
                    case "人":
                        perNumTv.setVisibility(View.VISIBLE);
                        nameTv.setText("姓名：" + fieldValues.get(7).getValue());
                        sexTv.setText("性别：" + fieldValues.get(9).getValue());
                        featureTv.setText("体态：" + fieldValues.get(10).getValue());
                        perNumTv.setText("身份证号：" + fieldValues.get(8).getValue());
                        unitTv.setText("稽查单位：" + fieldValues.get(11).getValue());
                        unitPersonTv.setText("联系人：" + fieldValues.get(12).getValue());
                        unitPhoneTv.setText("联系电话：" + fieldValues.get(13).getValue());
                        break;
                    case "车":
                        perNumTv.setVisibility(View.GONE);
                        nameTv.setText("品牌：" + fieldValues.get(7).getValue());
                        sexTv.setText("车牌：" + fieldValues.get(8).getValue());
                        featureTv.setText("颜色：" + fieldValues.get(9).getValue());
                        unitTv.setText("稽查单位：" + fieldValues.get(10).getValue());
                        unitPersonTv.setText("联系人：" + fieldValues.get(11).getValue());
                        unitPhoneTv.setText("联系电话：" + fieldValues.get(12).getValue());
                        break;
                    case "物":
                        perNumTv.setVisibility(View.GONE);
                        nameTv.setText("物品：" + fieldValues.get(7).getValue());
                        sexTv.setText("形状：" + fieldValues.get(8).getValue());
                        featureTv.setText("颜色：" + fieldValues.get(9).getValue());
                        unitTv.setText("稽查单位：" + fieldValues.get(10).getValue());
                        unitPersonTv.setText("联系人：" + fieldValues.get(11).getValue());
                        unitPhoneTv.setText("联系电话：" + fieldValues.get(12).getValue());
                        break;
                    default:
                        break;
                }
                byte[] input = Base64.decode(fieldValues.get(3).getValue(), Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(input, 0, input.length);
                coverIv.setImageBitmap(bitmap);
//                PicassoUtils.getinstance().loadImage(mContext, fieldValues.get(3).getValue(), coverIv, R.mipmap.default_image);
            } else if (res.getResult().getCode().equals("2")) {
                showTip("会话超时");
                AppContext.self().logout(mContext);
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
    protected void onDestroy() {
        super.onDestroy();
        uiHandle.removeMessages(1);
    }
}
