package com.qsmy.socket.config;

import com.qsmy.socket.constants.WebSocketConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author qsmy
 * @time 2021/10/7
 */
@Configuration
//表示开启使用STOMP协议来传输基于代理的消息，Broker就是代理的意思。
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 注册STOMP协议节点，同时指定使用SockJS协议。
        // 前端通过这个端点进行连接
        // 起到的作用就是添加一个服务端点，来接收客户端的连接
        registry.addEndpoint(WebSocketConstants.ENDPOINT)
                // 解决跨域问题
                .setAllowedOrigins("*")
                // 开启 SockJS 访问支持，即可通过http://IP:PORT/endpoint/ws 来和服务端 websocket 连接
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 阅Broker名称user点对点 topic广播即群发
        registry.enableSimpleBroker("/user","/topic");
        // 全局使用的订阅前缀(客户端订阅路径上会体现出来)
        registry.setApplicationDestinationPrefixes("/app");
        // 点对点使用的订阅前缀(客户端订阅路径上会体现出来)，不设置的话，默认也是/user/
        // 点对点使用的前缀 无需配置 默认/user
        registry.setUserDestinationPrefix("/user/");
    }
}
