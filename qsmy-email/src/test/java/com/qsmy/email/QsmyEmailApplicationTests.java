package com.qsmy.email;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class QsmyEmailApplicationTests {

    @Autowired
    private JavaMailSender mailSender;

    @Test
    void contextLoads() {
    }

    @Test
    public void test1() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("qsmy0424@163.com");
        message.setTo("qsmy0424@outlook.com", "178019018@qq.com", "wwhm0424@outlook.com");
        message.setSubject("ww");
        message.setText("my name is qsmy");
        mailSender.send(message);
    }
}
