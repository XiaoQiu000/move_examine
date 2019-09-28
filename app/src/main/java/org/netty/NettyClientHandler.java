package org.netty;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.RemoteViews;


import com.qiu.move_examine.R;
import com.qiu.move_examine.common.AppContext;
import com.qiu.move_examine.common.bean.CarBean;
import com.qiu.move_examine.common.bean.PersonBean;
import com.qiu.move_examine.common.bean.ThingsBean;
import com.qiu.move_examine.common.bean.UserInfoBean;
import com.qiu.move_examine.presenter.adapter.MessageAdapter;
import com.qiu.move_examine.presenter.event.NoticeEvent;
import com.qiu.move_examine.repertory.db.bean.NoticeInfo;
import com.satsoftec.frame.SFrame;
import com.satsoftec.frame.repertory.dbTool.DatabaseManage;

import org.greenrobot.eventbus.EventBus;
import org.netty.module.BaseMsg;
import org.netty.module.LoginMsg;
import org.netty.module.PingMsg;
import org.netty.module.PushMsg;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * @author 徐飞
 * @version 2016/02/25 14:11
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<BaseMsg> {
    private static final String TAG = "NettyClientHandler";
    //设置心跳时间  开始
    public static final int MIN_CLICK_DELAY_TIME = 1000 * 180;
    private long lastClickTime = 0;
    //设置心跳时间   结束

    //利用写空闲发送心跳检测消息
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case WRITER_IDLE:
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                        lastClickTime = System.currentTimeMillis();
                        PingMsg pingMsg = new PingMsg();
                        pingMsg.setClientId("45");
                        ctx.writeAndFlush(pingMsg);
                        System.out.println("send ping to server----------");
                    }
                    break;
                default:
                    break;
            }
        }
    }

    //这里是断线要进行的操作
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("重连了。---------");
        NettyClientBootstrap bootstrap = PushClient.getBootstrap();
        bootstrap.startNetty();
    }

    //这里是出现异常的话要进行的操作
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        System.out.println("出现异常了。。。。。。。。。。。。。");
        cause.printStackTrace();
    }

    //这里是接受服务端发送过来的消息
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, BaseMsg baseMsg) throws Exception {
        switch (baseMsg.getType()) {
            case LOGIN:
//                UserInfoBean userInfo = AppContext.userInfo;
//                //向服务器发起登录
//                LoginMsg loginMsg = new LoginMsg();
//                loginMsg.setPassword(userInfo.getPerPwd());
//                loginMsg.setUserName(userInfo.getPerNo());
//                channelHandlerContext.writeAndFlush(loginMsg);
                break;
            case PING:
                System.out.println("receive ping from server----------");
                break;
            case PUSH:

//                PushMsg pushMsg = (PushMsg) baseMsg;
//                Log.e(TAG, "messageReceived: 收到通知" + pushMsg.getExtMap());
//                NoticeBean json = SFrame.getGson().fromJson(pushMsg.getExtMap(), NoticeBean.class);
//
//                NoticeInfo noticeInfo = new NoticeInfo();
//                noticeInfo.setOwnerId(AppContext.userInfo.getId());
//                noticeInfo.setNoticeId(json.getId());
//                noticeInfo.setNoticeHaveRead(false);
//                DatabaseManage.insert(noticeInfo);
//                EventBus.getDefault().post(new NoticeEvent());
//                notification(AppContext.self().getApplication(), pushMsg.getTitle(), pushMsg.getContent());
                break;
            default:
                System.out.println("default..");
                break;
        }
        ReferenceCountUtil.release(baseMsg);
    }

    private void notification(Context context, String title, String content) {
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
        Intent Intent = new Intent();
        //设置广播
//        Intent.setAction("xxxx.xxxx.xxx.xxx.xxx");
        //为控件绑定事件
        mRemoteViews.setOnClickPendingIntent(R.id.fl_window_layout, PendingIntent.getBroadcast(context, 10, Intent, PendingIntent.FLAG_UPDATE_CURRENT));
        //更新Notification
        nm.notify(1, mBuilder.build());
    }


    private static class NoticeBean {
        private int id;
        private String targetType;//目标类型:01人02车03物
        private String monitorType;//布控类型:01抓捕02拦截03通知
        private String cover;//图片
        private String pushTime;

        private PersonBean person;//人
        private CarBean car;//车
        private ThingsBean things;//物

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTargetType() {
            return targetType;
        }

        public void setTargetType(String targetType) {
            this.targetType = targetType;
        }

        public String getMonitorType() {
            return monitorType;
        }

        public void setMonitorType(String monitorType) {
            this.monitorType = monitorType;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }


        public String getPushTime() {
            return pushTime;
        }

        public void setPushTime(String pushTime) {
            this.pushTime = pushTime;
        }

        public PersonBean getPerson() {
            return person;
        }

        public void setPerson(PersonBean person) {
            this.person = person;
        }

        public CarBean getCar() {
            return car;
        }

        public void setCar(CarBean car) {
            this.car = car;
        }

        public ThingsBean getThings() {
            return things;
        }

        public void setThings(ThingsBean things) {
            this.things = things;
        }
    }

}
