package com.qsmy.test.fork;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author qsmy
 */
public class Test {
    public static final Integer INTEGER = 1000;

    static final class SumTask extends RecursiveTask<Integer> {

        int start;
        int end;

        public SumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start < INTEGER) {
                System.out.println(Thread.currentThread().getName() + " 开始执行: " + start + "-" + end);
                int sum = 0;
                for (int i = start; i <= end; i++) {
                    sum += i;
                }
                return sum;
            }
            final SumTask sumTask1 = new SumTask(start, (end - start) / 2 + start);
            final SumTask sumTask2 = new SumTask((end - start) / 2 + start + 1, end);
            sumTask1.fork();
            sumTask2.fork();
            return sumTask1.join() + sumTask2.join();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        SumTask sumTask = new SumTask(1, 10000);
        pool.submit(sumTask);
        System.out.println(sumTask.get());
        System.out.println(0x7fff);
    }
}
