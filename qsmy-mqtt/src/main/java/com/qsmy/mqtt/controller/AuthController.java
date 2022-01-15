package com.qsmy.mqtt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qsmy
 */
@Slf4j
@RestController
public class AuthController {

    public static final String USERNAME = "qsmy";
    public static final String PASSWORD = "wwhm";

    @PostMapping("/mqtt/auth")
    public ResponseEntity<Object> auth(String clientid, String username, String password) {
        log.info("clientid:" + clientid);
        log.info("username:" + username);
        log.info("password:" + password);
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            return ResponseEntity.status(200).body("200");
        }
        return ResponseEntity.status(401).body("401");
    }
}
