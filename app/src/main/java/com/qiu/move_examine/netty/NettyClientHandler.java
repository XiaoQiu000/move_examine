package com.qiu.move_examine.netty;


import android.util.Log;


import com.qiu.move_examine.common.AppContext;
import com.qiu.move_examine.common.bean.UserInfoBean;
import com.qiu.move_examine.netty.module.BaseMsg;
import com.qiu.move_examine.netty.module.LoginMsg;
import com.qiu.move_examine.netty.module.PingMsg;
import com.qiu.move_examine.netty.module.PushMsg;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

/**
 * @author 徐飞
 * @version 2016/02/25 14:11
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<BaseMsg> {
    private static final String TAG = "NettyClientHandler";
    //设置心跳时间  开始
    public static final int MIN_CLICK_DELAY_TIME = 1000 * 30;
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
                UserInfoBean userInfo = AppContext.self().getUserInfo();
                //向服务器发起登录
                LoginMsg loginMsg = new LoginMsg();
                loginMsg.setPassword(userInfo.getPerPwd());
                loginMsg.setUserName(userInfo.getPerNo());
                channelHandlerContext.writeAndFlush(loginMsg);
                break;
            case PING:
                System.out.println("receive ping from server----------");
                break;
            case PUSH:

                PushMsg pushMsg = (PushMsg) baseMsg;
                Log.e(TAG, "messageReceived: " + pushMsg.getTitle());
                Log.e(TAG, "messageReceived: " + pushMsg.getContent());
//                Intent intent = new Intent(MainApplication.getAppContext(), MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("hi", pushMsg.getContent());
//                MainApplication.getAppContext().startActivity(intent);
//                System.out.println(pushMsg.getTitle() + " , " +pushMsg.getContent());
                break;
            default:
                System.out.println("default..");
                break;
        }
        ReferenceCountUtil.release(baseMsg);
    }
}
