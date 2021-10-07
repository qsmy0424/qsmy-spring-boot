package com.qsmy.socket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qsmy
 * @time 2021/10/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebMessage {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 客户端标记
     */
    private String clientMark;

    /**
     * 内容
     */
    private String contents;

    /**
     * 消息类型，1.公告，2.点对点发消息，3.检查异地登录
     */
    private String type;
}
