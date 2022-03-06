package com.qsmy.test.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author qsmy
 */
@Service
public class Service3 {
    @Autowired
    private Service1 service1;
    @Autowired
    private Service2 service2;
    @Autowired
    private Service3 service3;

    @Transactional
    public void test() {
        service1.test(1);
        try {
            service3.childTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(propagation = Propagation.NESTED)
    public void childTest() {
        service2.test(2);
        throw new RuntimeException();
    }

}
