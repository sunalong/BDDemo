package com.itcode.testzookeeper.distributedLock;

import com.oracle.tools.packager.Log;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by along on 17/9/14 10:37.
 * Email:466210864@qq.com
 */
public class DistributedLock {
    private ZooKeeper zooKeeper;

    private final Logger LOG = LoggerFactory.getLogger(DistributedLock.class);
    private String CURRENT_THREAD_NAME = Thread.currentThread().getName();
    public DistributedLock(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    /**
     * 创建子节点所在的父节点
     *
     * @param zkLockGroupPath
     * @param data
     */
    public boolean createGroupPath(String zkLockGroupPath, String data) {
        try {//Todo:加锁
            if (zooKeeper.exists(zkLockGroupPath, false) == null) {
                String parentNodePath = zooKeeper.create(zkLockGroupPath, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                Log.info(CURRENT_THREAD_NAME+"创建组节点成功，path:"+parentNodePath+" data:"+data);
            }
        } catch (KeeperException|InterruptedException e) {
            if(e instanceof KeeperException.NodeExistsException){
                LOG.info("组节点已经完成，"+CURRENT_THREAD_NAME+"不必再创建");
                return true;
            }
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断当前znode是否可执行
     *
     * @return
     */
    public boolean currentCanExec() {
        return true;
    }

    /**
     * 释放本节点所点资源
     */
    public void release() {

    }
}
