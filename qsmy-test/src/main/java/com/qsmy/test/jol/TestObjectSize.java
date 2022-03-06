package com.qsmy.test.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author qsmy
 */
public class TestObjectSize {

    public static void main(String[] args) {
        Son son = new Son();
        System.out.println(ClassLayout.parseInstance(new int[2]).toPrintable());
    }
}
