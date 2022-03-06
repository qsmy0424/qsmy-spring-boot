package com.qsmy.test;

import java.util.Stack;

/**
 * @author qsmy
 */
class MyQueue {
    private Stack s1;
    private Stack s2;

    public MyQueue() {
        s1 = new Stack();
        s2 = new Stack();
    }

    public void push(int x) {
        s1.push(x);
    }

    public int pop() {
        operate();

        return ((int) s2.pop());
    }

    public int peek() {
        operate();
        return ((int) s2.peek());
    }

    public boolean empty() {
        operate();
        return s2.isEmpty();
    }
    private void operate() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
    }

    @Override
    public String toString() {
        return "MyQueue{" +
                "s2=" + s2 +
                '}';
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1); // queue is: [1]
        System.out.println(myQueue);
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        myQueue.peek(); // return 1
        myQueue.pop(); // return 1, queue is [2]
        myQueue.empty(); // return false
    }
}
