package com.qiu.move_examine.presenter.service;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import com.qiu.move_examine.R;
import com.qiu.move_examine.common.AppContext;
import com.qiu.move_examine.common.ClientConstant;
import com.qiu.move_examine.common.bean.CarBean;
import com.qiu.move_examine.common.bean.PersonBean;
import com.qiu.move_examine.common.bean.ThingsBean;
import com.qiu.move_examine.common.utils.PicassoUtils;
import com.qiu.move_examine.presenter.activity.MessageDetailsActivity;
import com.qiu.move_examine.presenter.event.NoticeEvent;
import com.qiu.move_examine.presenter.receiver.NoticeReceiver;
import com.qiu.move_examine.repertory.db.bean.NoticeInfo;
import com.qiu.move_examine.repertory.webservice.response.QueryResponse;
import com.qiu.move_examine.repertory.webservice.service.CommonService;
import com.satsoftec.frame.SFrame;
import com.satsoftec.frame.repertory.dbTool.DatabaseManage;
import com.satsoftec.frame.repertory.remote.WebServiceManage;
import com.satsoftec.frame.repertory.remote.callback.SCallBack;
import com.satsoftec.frame.util.SharedPreferenceUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class NoticeService extends Service {
    private static final String TAG = "NoticeService";
    private int time = 10 * 1000;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 采用AlarmManager机制
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(time);
                        WebServiceManage.getService(CommonService.class).pushList().setCallback(new SCallBack<QueryResponse>() {
                            @Override
                            public void callback(boolean isok, String msg, QueryResponse res) {
                                if (!isok) {
                                    return;
                                }
                                if (res.getResult() != null) {
                                    if (res.getResult().getCode().equals("1")) {
                                        Log.e("NoticeService", "run: 获取推送消息成功");
                                        for (int i = 0; i < res.getResult().getData().size(); i++) {
                                            NoticeInfo noticeInfo = new NoticeInfo();
                                            QueryResponse.ResultBean.DataBean dataBean = res.getResult().getData().get(i);
                                            List<QueryResponse.ResultBean.DataBean.FieldValuesBean> tempBean = dataBean.getFieldValues();
                                            String userId = SharedPreferenceUtil.getSharedPreString(ClientConstant.SPREFERENCES_LOGIN_ID);
                                            noticeInfo.setOwnerId(userId);
                                            noticeInfo.setNoticeId(Integer.parseInt(tempBean.get(0).getValue()));
                                            noticeInfo.setMonitorType(tempBean.get(1).getValue());
                                            noticeInfo.setTargetType(tempBean.get(2).getValue());
                                            noticeInfo.setCover(tempBean.get(3).getValue());
                                            noticeInfo.setPushTime(tempBean.get(6).getValue());
                                            switch (tempBean.get(2).getValue()) {
                                                case "人":
                                                    PersonBean personBean = new PersonBean();
                                                    personBean.setPerName(tempBean.get(7).getValue());
                                                    personBean.setPerIdNo(tempBean.get(8).getValue());
                                                    personBean.setPerSex(tempBean.get(9).getValue());
                                                    personBean.setPerFigure(tempBean.get(10).getValue());
                                                    noticeInfo.setPerson(SFrame.getGson().toJson(personBean));
                                                    break;
                                                case "车":
                                                    CarBean carBean = new CarBean();
                                                    carBean.setCarNo(tempBean.get(7).getValue());
                                                    carBean.setBrand(tempBean.get(8).getValue());
                                                    carBean.setColor(tempBean.get(9).getValue());
                                                    noticeInfo.setCar(SFrame.getGson().toJson(carBean));
                                                    break;
                                                case "物":
                                                    ThingsBean thingsBean = new ThingsBean();
                                                    thingsBean.setItems(tempBean.get(7).getValue());
                                                    thingsBean.setShape(tempBean.get(8).getValue());
                                                    thingsBean.setColor(tempBean.get(9).getValue());
                                                    noticeInfo.setThings(SFrame.getGson().toJson(thingsBean));
                                                    break;
                                                default:
                                                    break;
                                            }
                                            noticeInfo.setNoticeHaveRead(false);
                                            DatabaseManage.insert(noticeInfo);
                                            EventBus.getDefault().post(new NoticeEvent());
                                            notification(AppContext.self().getApplication(), tempBean.get(4).getValue(), tempBean.get(5).getValue(), Integer.parseInt(tempBean.get(0).getValue()));
                                        }
                                    }
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
//        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        long triggerAtTime = SystemClock.elapsedRealtime() + time;
//        Intent intent2 = new Intent(this, NoticeReceiver.class);
//        PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent2, 0);
//        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }


    private void notification(Context context, String title, String content, int id) {
        //1.获取系统通知的管理者
        NotificationManager nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        //2.初始化一个notification的对象
        Notification.Builder mBuilder = new Notification.Builder(context);
        //android 8.0 适配     需要配置 通知渠道NotificationChannel
        NotificationChannel b;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            b = new NotificationChannel("1", "通知信息", NotificationManager.IMPORTANCE_MIN);
            nm.createNotificationChannel(b);
            mBuilder.setChannelId("1");
        }
        //添加自定义视图  activity_notification
        RemoteViews mRemoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_notification);
        mRemoteViews.setTextViewText(R.id.title, title);
        mRemoteViews.setTextViewText(R.id.content, content);
        //主要设置setContent参数  其他参数 按需设置
        mBuilder.setContent(mRemoteViews);
        mBuilder.setSmallIcon(R.mipmap.icon_police);
        mBuilder.setOngoing(true);
        mBuilder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon_police));
        mBuilder.setAutoCancel(true);
        Intent intent = new Intent(context, MessageDetailsActivity.class);
        intent.putExtra("mId", id + "");
        mBuilder.setContentIntent(PendingIntent.getActivity(context, 10, intent, PendingIntent.FLAG_UPDATE_CURRENT));
//        //为控件绑定事件
//        mRemoteViews.setOnClickPendingIntent(R.id.fl_window_layout, PendingIntent.getBroadcast(context, 10, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        //更新Notification
        nm.notify(1, mBuilder.build());

    }

}
