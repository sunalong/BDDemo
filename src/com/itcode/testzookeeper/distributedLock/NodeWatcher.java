package com.itcode.testzookeeper.distributedLock;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by along on 17/9/14 15:29.
 * Email:466210864@qq.com
 * 用以观察节点的状态
 */
public class NodeWatcher implements Watcher {
    private Logger LOG = LoggerFactory.getLogger(NodeWatcher.class);
    @Override
    public void process(WatchedEvent event) {
//        if(event.getType() == Event.EventType.NodeDeleted && event.getPath().equals())
        if(event.getType() == Event.EventType.NodeDeleted){
            LOG.info(Thread.currentThread().getName()+"所观察的节点被删除");

        }
    }
}
