package com.example.mqttfactorydemo.controller;

/**
 * @author peiyuxiang
 * @date 2022/6/15
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author peiyuxiang
 * @ServerEndPoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端，
 * 注解的值将被用于监听用户连接的终端访问URL地址，客户端可以通过这个URL连接到websocket服务器端
 */
@ServerEndpoint("/websocket/{type}")
@Component("webSocketTest")
@Slf4j
public class WebSockTest {
    private static int onlineCount = 0;
    /**
     * 使用map，对WebSocketSet进行分组，根据key来判断要推送到哪里
     */
    private static final Map<String, CopyOnWriteArraySet<WebSockTest>> WEB_SOCKET_SETS = new HashMap<>();
    private Session session;


    @OnOpen
    public void onOpen(@PathParam("type") String type, Session session) {
        this.session = session;
        // 加入set中
        if (WEB_SOCKET_SETS.containsKey(type)) {
            WEB_SOCKET_SETS.get(type).add(this);
        } else {
            CopyOnWriteArraySet<WebSockTest> webSocketSet = new CopyOnWriteArraySet<WebSockTest>();
            webSocketSet.add(this);
            WEB_SOCKET_SETS.put(type, webSocketSet);
        }
        addOnlineCount();
        log.info("=======WebSocket======= 有新连接加入！当前在线人数为 {}", getOnlineCount());
    }

    @OnClose
    public void onClose(@PathParam("type") String type) {
        CopyOnWriteArraySet<WebSockTest> set = WEB_SOCKET_SETS.get(type);
        set.remove(this);
        subOnlineCount();
        log.info("=======WebSocket======= 有一连接关闭！当前在线人数为 {}", getOnlineCount());
    }

    @OnMessage
    public void onMessage(@PathParam("type") String type, String message) {
        CopyOnWriteArraySet<WebSockTest> webSockTests = WEB_SOCKET_SETS.get(type);
//        群发消息
        webSockTests.forEach(item -> {
            try {
                item.sendMessage(message);

            } catch (IOException e) {
                log.error("=======WebSocket======= 发送消息错误", e);
            }
        });
    }


    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("发生错误！");
        throwable.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        if (this != null && this.session != null
                && this.session.getBasicRemote() != null) {
            this.session.getBasicRemote().sendText(message);
        }
        log.info("=======WebSocketServer======= 实时推送消息成功 : {}", message);
    }

    public void sendData(String message, String type) throws IOException {
        if (!StringUtils.hasText(type)) {
            return;
        }
        CopyOnWriteArraySet<WebSockTest> webSockTests = WEB_SOCKET_SETS.get(type);
        if (!CollectionUtils.isEmpty(webSockTests)) {
            webSockTests.forEach(item -> {
                try {
                    item.sendMessage(message);

                } catch (IOException e) {
                    log.error("=======WebSocket======= 发送消息错误", e);
                }
            });
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WebSockTest that = (WebSockTest) o;
        return Objects.equals(session, that.session);
    }

    @Override
    public int hashCode() {
        return Objects.hash(session);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSockTest.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSockTest.onlineCount--;
    }


}

