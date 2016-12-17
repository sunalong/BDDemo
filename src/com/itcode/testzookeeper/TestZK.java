package com.itcode.testzookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;

import java.util.List;

/**
 * Created by along on 16/12/17.
 */
public class TestZK {
    private static String connectString = "mini1:2181,mini2:2181,mini3:2181";
    private static int sessionTimeout = 2000;
    ZooKeeper zooKeeper;

    public void init() throws Exception {
        zooKeeper = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event.getType() + " iniit--" + event.getPath());
                try {
                    zooKeeper.getChildren("/", true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 创建节点
     *
     * @throws Exception
     */
    public void createZnode(final String path, byte data[], List<ACL> acl, CreateMode createMode) throws Exception {
        String nodeCreated = zooKeeper.create(path, data, acl, createMode);
    }

    /**
     * 获取节点的数据
     *
     * @param path
     * @param watch
     * @throws Exception
     */
    public void getZnodeData(String path, boolean watch) throws Exception {
        List<String> childrenList = zooKeeper.getChildren(path, watch);
        for (String child : childrenList) {
            System.out.println(child);
        }
        Thread.sleep(Long.MAX_VALUE);
    }

}

