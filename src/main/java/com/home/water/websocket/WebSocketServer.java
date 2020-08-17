package com.home.water.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: xu.dm
 * @Date: 2020/8/14 16:24
 * @Version: 1.0
 * @Description: 消息推送服务
 **/
@Slf4j
@ServerEndpoint("/webSocket/{code}")
@Component
public class WebSocketServer {
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的WebSocket对象。
     */
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

    /**
     * 与客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 接收识别码
     */
    private String code = "";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("code") String code) {
        this.session = session;
        //如果存在就先删除一个，防止重复推送消息
        webSocketSet.removeIf(webSocket -> webSocket.code.equals(code));
        webSocketSet.add(this);
        this.code = code;
        log.info("建立WebSocket连接，code：" + code+"，当前连接数："+webSocketSet.size());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        log.info("关闭WebSocket连接，code：" + this.code+"，当前连接数："+webSocketSet.size());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来[" + code + "]的信息:" + message);
        //群发消息
        for (WebSocketServer socket : webSocketSet) {
            try {
                socket.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("websocket发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 群发自定义消息
     */
    public void sendAll(String message) {
        log.info("推送消息到" + code + "，推送内容:" + message);
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 定点推送
     */
    public void sendTo(String message, @PathParam("code") String code) {
        for (WebSocketServer item : webSocketSet) {
            try {
                if (item.code.equals(code)) {
                    log.info("推送消息到[" + code + "]，推送内容:" + message);
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        WebSocketServer that = (WebSocketServer) o;
        return Objects.equals(session, that.session) &&
                Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(session, code);
    }
}
