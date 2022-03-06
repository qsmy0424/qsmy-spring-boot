package com.qsmy.test;

import com.qsmy.test.transaction.Service3;
import org.apache.coyote.OutputBuffer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QsmyTestApplicationTests {

    @Autowired
    private Service3 service3;

    @Test
    void contextLoads() {
        System.out.println(123);
    }

    @Test
    public void test1() {
        service3.test();
    }

    @Test
    public void test2() {
        System.out.println("q".length());
        System.out.println("中".length());
        System.out.println("眸".length());
        System.out.println("\uD834\uDD1E");
        System.out.println("\uD834\uDD1E".codePointCount(0, "\uD834\uDD1E".length()));
    }

}
