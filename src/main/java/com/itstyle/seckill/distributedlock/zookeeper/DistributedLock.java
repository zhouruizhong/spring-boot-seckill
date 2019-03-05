package com.itstyle.seckill.distributedlock.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author 周瑞忠
 * @description java类作用描述
 * @date 2019/2/28 14:31
 */
public class DistributedLock implements Watcher, Lock {

    private ZooKeeper zooKeeper = null;
    /**
     * 根节点
     */
    private String ROOT_LOCK = "/locks";
    /**
     * 竞争的资源
     */
    private String lockName;
    /**
     * 等待的前一个锁
     */
    private String WAIT_LOCK;
    /**
     * 当前锁
     */
    private String CURRENT_LOCK;
    /**
     * 计数器
     */
    private CountDownLatch countDownLatch;
    /**
     * 超时
     */
    private int sessionTimeout;
    private List<Exception> exceptionList = new ArrayList<Exception>();

    public DistributedLock(String config, String lockName){
        this.lockName = lockName;
        try {
            // 连接zookeeper
            zooKeeper = new ZooKeeper(config, sessionTimeout, this);
            Stat stat = zooKeeper.exists(ROOT_LOCK, false);
            if (null == stat){
                zooKeeper.create(ROOT_LOCK, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void lock() {
        if (exceptionList.size() > 0) {
            throw new LockException(exceptionList.get(0));
        }
        try {
            if (this.tryLock()){
                System.out.println(Thread.currentThread().getName() + " " + lockName + "获得了锁");
            }else {
                // 等待锁

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        this.lock();
    }

    @Override
    public boolean tryLock() {
        try {
            String splitStr = "_lock_";
            if (lockName.contains(splitStr)){
                throw new LockException("锁名有误！");
            }

            // 创建临时有序节点
            CURRENT_LOCK = zooKeeper.create(ROOT_LOCK + "/" + lockName + splitStr, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(CURRENT_LOCK + "创建成功！");
            // 取所有子节点
            List<String> subNodes = zooKeeper.getChildren(ROOT_LOCK, false);
            // 取出所有lockName 的锁
            List<String> lockObjects = new ArrayList<>();
            for (String node : subNodes) {
                String _node = node.split(splitStr)[0];
                if (_node.equals(lockName)){
                    lockObjects.add(_node);
                }
            }
            Collections.sort(lockObjects);
            System.out.println(Thread.currentThread().getName() + " 的锁是" + CURRENT_LOCK);

            // 若当前节点最小，则获取锁成功
            if (CURRENT_LOCK.equals(ROOT_LOCK + "/" + lockObjects.get(0))){
                return true;
            }
            // 若不是，则找到自己的前一个节点
            String preNode = CURRENT_LOCK.substring(CURRENT_LOCK.lastIndexOf("/") + 1);
            WAIT_LOCK = lockObjects.get(Collections.binarySearch(lockObjects, preNode) - 1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (KeeperException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        try {
            if (tryLock()){
                return true;
            }
            return waitLock(WAIT_LOCK, time);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private boolean waitLock(String lockName, long timeout){

        return false;
    }

    @Override
    public void unlock() {
        try {
            System.out.println("释放锁" + CURRENT_LOCK);
            zooKeeper.delete(CURRENT_LOCK, -1);
            CURRENT_LOCK = null;
            zooKeeper.close();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (KeeperException e){
            e.printStackTrace();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    /**
     * 节点监视器
     */
    @Override
    public void process(WatchedEvent watchedEvent) {
        if (null != this.countDownLatch){
            this.countDownLatch.countDown();
        }
    }

    public class LockException extends RuntimeException {
        private static final long serialVersionUID = 1L;
        public LockException(String e){
            super(e);
        }
        public LockException(Exception e){
            super(e);
        }
    }
}
