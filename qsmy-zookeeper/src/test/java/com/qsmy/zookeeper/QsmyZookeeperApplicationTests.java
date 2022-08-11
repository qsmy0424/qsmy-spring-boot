package com.qsmy.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.zookeeper.CreateMode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class QsmyZookeeperApplicationTests {

    @Autowired
    CuratorFramework curatorFramework;

    /**
     * 创建节点
     */
    @Test
    void createNode() throws Exception {
        //添加持久节点
        String path = curatorFramework.create().forPath("/curator-node");
        //添加临时序号节点
        String path1 =
                curatorFramework.create()
                        .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                        .forPath("/curator-node", "some-data".getBytes());
        System.out.println(path1);
        System.in.read();
    }

    /**
     * 获取节点数据
     */
    @Test
    public void testGetData() throws Exception {
        byte[] bytes = curatorFramework.getData().forPath("/curator-node");
        System.out.println(new String(bytes));
    }

    /**
     * 修改节点数据
     */
    @Test
    public void testSetData() throws Exception {
        curatorFramework.setData().forPath("/curator-node", " changed !".getBytes());
        byte[] bytes = curatorFramework.getData().forPath("/curator-node");
        System.out.println(new String(bytes));
    }

    /**
     * 创建节点同时创建⽗节点
     */
    @Test
    public void testCreateWithParent() throws Exception {
        String pathWithParent = "/node-parent/sub-node-1";
        String path =
                curatorFramework.create().creatingParentsIfNeeded().forPath(pathWithParent);
        System.out.printf("curator create node :%s successfully.%n", path);
    }

    /**
     * 删除节点
     */
    @Test
    public void testDelete() throws Exception {
        String pathWithParent = "/node-parent";
        curatorFramework.delete().guaranteed().deletingChildrenIfNeeded().forPath(pathWithParent);
    }

    @Test
    void testGetReadLock() throws Exception {
        // 读写锁
        InterProcessReadWriteLock interProcessReadWriteLock =
                new InterProcessReadWriteLock(curatorFramework, "/lock1");
        // 获取读锁对象
        InterProcessLock interProcessLock = interProcessReadWriteLock.readLock();
        System.out.println("等待获取读锁对象!");
        // 获取锁
        interProcessLock.acquire();
        for (int i = 1; i <= 100; i++) {
            Thread.sleep(3000);
            System.out.println(i);
        }
        // 释放锁
        interProcessLock.release();
        System.out.println("等待释放锁!");
    }

    @Test
    void testGetWriteLock() throws Exception {
        // 读写锁
        InterProcessReadWriteLock interProcessReadWriteLock =
                new InterProcessReadWriteLock(curatorFramework, "/lock1");
        // 获取写锁对象
        InterProcessLock interProcessLock = interProcessReadWriteLock.writeLock();
        System.out.println("等待获取写锁对象!");
        // 获取锁
        interProcessLock.acquire();
        for (int i = 1; i <= 100; i++) {
            Thread.sleep(3000);
            System.out.println(i);
        }
        // 释放锁
        interProcessLock.release();
        System.out.println("等待释放锁!");
    }

    @Test
    public void addNodeListener() throws Exception {
        NodeCache nodeCache = new NodeCache(curatorFramework, "/curator-node");
        nodeCache.getListenable().addListener(() -> {
            log.info("{} path nodeChanged: ", "/curator-node");
            printNodeData();
        });
        nodeCache.start();
        System.in.read();
    }

    public void printNodeData() throws Exception {
        byte[] bytes = curatorFramework.getData().forPath("/curator-node");
        log.info("data: {}", new String(bytes));
    }
}
