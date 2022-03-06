package com.qsmy.test.jol;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author qsmy
 */
@Slf4j
public class Test5 {
    public static void main(String[] args) throws InterruptedException {
        // 睡眠 5s
        Thread.sleep(5000);

        Object o = new Object();
        log.info("未生成 hashcode，MarkWord 为：");
        log.info(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) {
            log.info(("进入同步块，MarkWord 为："));
            log.info(ClassLayout.parseInstance(o).toPrintable());
        }

        o.hashCode();
        log.info("生成 hashcode");
        synchronized (o) {
            log.info(("同一线程再次进入同步块，MarkWord 为："));
            log.info(ClassLayout.parseInstance(o).toPrintable());
        }
    }

}
