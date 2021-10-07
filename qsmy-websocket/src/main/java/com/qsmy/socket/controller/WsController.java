package com.qsmy.socket.controller;

import com.qsmy.socket.entity.WebMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

/**
 * @author qsmy
 * @time 2021/10/7
 */
@Controller
public class WsController {

    private final SimpMessagingTemplate messagingTemplate;


    @Autowired
    public WsController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }


    /**
     * 发送公告
     */
    @MessageMapping("/to-all")
    public void toAll(WebMessage msg) {
        messagingTemplate.convertAndSend("/topic/to-all", "123");
    }

    /**
     * 发送指定用户
     */
    @MessageMapping("/to-one")
    public void toOne(WebMessage msg) {
        Optional.ofNullable(msg.getUserId())
                .ifPresent(userId ->
                        messagingTemplate.convertAndSendToUser(userId.toString(), "/to-one", msg));
    }

    /**
     * 跳转至hello.html界面
     *
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    /**
     * 接收然后转发至客户端消息
     */
    @MessageMapping("/top")
    @SendTo("/topic/greetings")
    public String greeting(String message) {
        System.out.println("receiving " + message);
        System.out.println("connecting successfully.");
        return "AAA:" + message;
    }

    /**
     * 后端向客户端推送消息
     */
    @ResponseBody
    @RequestMapping("/hello/addMessage")
    public Object addMessage() {
        messagingTemplate.convertAndSend("/topic/greetings", "您收到了新的系统消息");
        return "添加成功";
    }
}
