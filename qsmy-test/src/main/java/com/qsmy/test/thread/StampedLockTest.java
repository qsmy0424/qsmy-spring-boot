package com.qsmy.test.thread;

import java.util.concurrent.locks.StampedLock;

/**
 * @author qsmy
 */
public class StampedLockTest {

    private double x, y;
    private final StampedLock s1 = new StampedLock();

    public void remove(double deltaX, double deltaY) {
        final long stamp = s1.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            s1.unlockWrite(stamp);
        }
    }

    /**
     * 乐观读
     */
    public double distanceFromOrigin() {

        // 获取一个乐观读锁
        long stamp = s1.tryOptimisticRead();
        //将两个字段读入本地局部变量
        double currentX = x;
        double currentY = y;
        // 检查发出乐观读锁后同时是否有其它写锁发生
        if (!s1.validate(stamp)) {
            // 如果有，我们再次获取一个读悲观锁
            stamp = s1.readLock();
            try {
                // 将两个字段读入本地局部变量
                currentX = x;
                currentY = y;
            } finally {
                s1.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    /**
     * 悲观读
     */
    public void moveIfAtOrigin(double newX, double newY) {
        long stamp = s1.readLock();
        // 循环，检查当前状态是否符合
        try {
            while (x == 0.0 && y == 0.0) {
                long ws = s1.tryConvertToWriteLock(stamp);
                // 确认转换写锁是否成功
                if (ws != 0L) {
                    // 如果成功，替换票据
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                } else {
                    // 如果不能成功转化写锁
                    s1.unlockRead(stamp);
                    // 显式直接进行写锁 然后再通过循环再试
                    stamp = s1.writeLock();
                }
            }
        } finally {
            //释放读锁或写锁
            s1.unlock(stamp);
        }
    }

}
