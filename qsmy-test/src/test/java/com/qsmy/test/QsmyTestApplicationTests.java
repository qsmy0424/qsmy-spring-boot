package com.qsmy.test;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QsmyTestApplicationTests {


    @Autowired
    private HttpServer httpServerQsmyServer;

    @Test
    void contextLoads() {
        System.out.println(httpServerQsmyServer.getAddress());
        System.out.println(123);
    }

}
