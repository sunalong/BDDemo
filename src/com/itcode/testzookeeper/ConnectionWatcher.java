package com.itcode.testzookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by along on 17/9/6 18:11.
 * Email:466210864@qq.com
 */
public class ConnectionWatcher implements Watcher {
    private static final int SESSION_TIMEOUT = 5000;
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    protected ZooKeeper zk;

    public void connect(String host) throws IOException, InterruptedException {
        zk = new ZooKeeper(host, SESSION_TIMEOUT, this);
        countDownLatch.await();
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected) {
            countDownLatch.countDown();
        }
    }

    public void close() throws InterruptedException {
        zk.close();
    }
}
