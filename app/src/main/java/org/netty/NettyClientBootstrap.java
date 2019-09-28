package org.netty;


import android.util.Log;

import com.google.gson.Gson;
import com.qiu.move_examine.common.AppContext;
import com.qiu.move_examine.common.bean.UserInfoBean;

import org.netty.module.Constants;
import org.netty.module.LoginMsg;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @author 徐飞
 * @version 2016/02/25 14:11
 */
public class NettyClientBootstrap {
    private static final String TAG = "NettyClientBootstrap";
    private int port = 9999;
    private String host = "192.168.0.111";
    public SocketChannel socketChannel;
    private static final EventExecutorGroup group = new DefaultEventExecutorGroup(20);

    public void startNetty() throws InterruptedException {

//        if (socketChannel != null && socketChannel.isOpen()) {
//            System.out.println("已经连接");
//        } else {
//            Constants.setClientId(userInfo.getId());
//            System.out.println("长链接开始");
//            if (start()) {
//                LoginMsg loginMsg = new LoginMsg();
//                loginMsg.setPassword(userInfo.getPerPwd());
//                loginMsg.setUserName(userInfo.getPerNo());
//                String s = new Gson().toJson(loginMsg);
//                Log.e(TAG, "startNetty: " + s);
//                socketChannel.writeAndFlush(loginMsg);
//                System.out.println("长链接成功");
//            } else {
//                System.out.println("长链接失败...");
//            }
//        }
    }

    private Boolean start() throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.group(eventLoopGroup);
        bootstrap.remoteAddress(host, port);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new IdleStateHandler(20, 10, 0));
                socketChannel.pipeline().addLast(new ObjectEncoder());
                socketChannel.pipeline().addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                socketChannel.pipeline().addLast(new NettyClientHandler());
            }
        });
        ChannelFuture future = null;
        try {
            future = bootstrap.connect(new InetSocketAddress(host, port)).sync();
            if (future.isSuccess()) {
                socketChannel = (SocketChannel) future.channel();
                System.out.println("connect server  成功---------");
                return true;
            } else {
                System.out.println("connect server  失败---------");
                startNetty();
                return false;
            }
        } catch (Exception e) {
            System.out.println("无法连接----------------");
            return false;
        }
    }

    public void closeChannel() {
        if (socketChannel != null) {
            socketChannel.close();
        }
    }

    public boolean isOpen() {
        if (socketChannel != null) {
            System.out.println(socketChannel.isOpen());
            return socketChannel.isOpen();
        }
        return false;
    }
}