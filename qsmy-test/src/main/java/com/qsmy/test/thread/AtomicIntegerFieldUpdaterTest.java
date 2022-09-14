package com.qsmy.test.thread;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.stream.IntStream;

/**
 * @author qsmy
 * @time 2022/9/9
 */
public class AtomicIntegerFieldUpdaterTest {

    public static void main(String[] args) {
        Student student = new Student();
        AtomicIntegerFieldUpdater<Student> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Student.class, "age");

        IntStream.range(1, 10000).forEach(
                i -> {
                    Thread thread = new Thread(() -> fieldUpdater.incrementAndGet(student));
                    thread.start();
                }
        );

        System.out.println(student);
    }
}

class Student {
    public volatile int age;
    public String name;

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}