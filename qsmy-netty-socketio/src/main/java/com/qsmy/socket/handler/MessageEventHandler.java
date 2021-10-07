package com.qsmy.socket.handler;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.qsmy.socket.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qsmy
 * @time 2021/9/4
 */
@Slf4j
@Component
public class MessageEventHandler {

    protected static final Map<String, SocketIOClient> CLIENT_UUID_MAP = new ConcurrentHashMap<>();

    @OnConnect
    public void onConnect(SocketIOClient client) {
        final UUID sessionId = client.getSessionId();
        CLIENT_UUID_MAP.put(sessionId.toString(), client);
        System.out.println(sessionId);
        System.out.println(client.getNamespace().getName());
        System.out.println(client.getHandshakeData().getSingleUrlParam("mac"));
        System.out.println(client.getAllRooms());
        System.out.println(client.getRemoteAddress());
        System.out.println(client.getTransport());

        client.sendEvent("connect-qsmy", sessionId + "已连接！！！！");
        log.info("客户端：" + sessionId + "连接。");
    }

    @OnDisconnect
    public void disConnect(SocketIOClient client) {
        CLIENT_UUID_MAP.remove(client.getSessionId().toString());
        log.info("客户端：" + client.getSessionId() + "断开连接。");
    }

    @OnEvent("chatevent")
    public void onEvent(SocketIOClient client, Message message) {
        final String sessionId = client.getSessionId().toString();
        client.sendEvent("chatevent", "server:" + message.getMessage());
        sendBroadcast(sessionId, message.getMessage());
    }

    private void sendBroadcast(String sessionId, String message) {
        CLIENT_UUID_MAP.forEach((uuid, client) -> client.sendEvent("chatevent", sessionId + ":" + message));
    }
}
