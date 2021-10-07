package com.qsmy.socket.config;

import cn.hutool.core.util.StrUtil;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qsmy
 * @time 2021/9/4
 */
@EnableConfigurationProperties({WsConfig.class})
@Configuration
public class NettySocketIoConfig {

    @Bean
    public SocketIOServer socketIoServer(WsConfig wsConfig) {
        com.corundumstudio.socketio.Configuration configuration = new com.corundumstudio.socketio.Configuration();
        configuration.setPort(wsConfig.getPort());
        configuration.setHostname(wsConfig.getHost());
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setReuseAddress(true);
        configuration.setSocketConfig(socketConfig);

        configuration.setAuthorizationListener(data -> {
            // http://localhost:18083?token=xxxxxxx
            // 例如果使用上面的链接进行connect，可以使用如下代码获取用户密码信息，本文不做身份验证
            String token = data.getSingleUrlParam("token");
            // 校验token的合法性，实际业务需要校验token是否过期等等，参考 spring-boot-demo-rbac-security 里的 JwtUtil
            // 如果认证不通过会返回一个 Socket.EVENT_CONNECT_ERROR 事件
            return StrUtil.isNotBlank(token);
        });
        return new SocketIOServer(configuration);
    }

    /**
     * Spring 扫描自定义注解
     */
    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketIoServer) {
        return new SpringAnnotationScanner(socketIoServer);
    }
}
