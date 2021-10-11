package com.qsmy.async;

import com.qsmy.async.task.AsyncTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
class QsmyAsyncApplicationTests {

    @Autowired
    private AsyncTask asyncTask;

    @Test
    void contextLoads() {
    }

    @Test
    public void test() {
        asyncTask.test();
    }

    @Test
    public void test1() {
        IntStream.rangeClosed(1, 100).forEach(asyncTask::task);
    }

}
