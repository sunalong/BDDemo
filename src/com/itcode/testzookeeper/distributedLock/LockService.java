package com.itcode.testzookeeper.distributedLock;

import com.itcode.testzookeeper.ConnectionWatcher;
import com.itcode.testzookeeper.rmi.Constants;
import org.apache.zookeeper.ZooKeeper;

/**
 * Created by along on 17/9/14 10:30.
 * Email:466210864@qq.com
 */
public class LockService {
    public void doService(IDoTemplete iDoTemplete) {
        ConnectionWatcher connectionWatcher = new ConnectionWatcher();
        ZooKeeper zooKeeper = connectionWatcher.connect(Constants.ZK_LOCK_HOSTS);
        DistributedLock distributedLock = new DistributedLock(zooKeeper,iDoTemplete);
        String data = "该节点由线程"+Thread.currentThread().getName()+"创建";
        distributedLock.createGroupPath(Constants.ZK_LOCK_GROUP_PATH,data);
        boolean canExec = distributedLock.currentCanExec();
        if(canExec){
            boolean haveDone = iDoTemplete.doSomething();
            TestLock.threadSemaphore.countDown();
            if(haveDone)
                distributedLock.releaseDoneNode();
        }
    }
}
