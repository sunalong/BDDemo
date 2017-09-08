package com.itcode.testzookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;

import java.io.IOException;

/**
 * Created by along on 17/9/8 11:47.
 * Email:466210864@qq.com
 */
public class JoinGroup extends ConnectionWatcher {
    public void join(String groupName, String memberName) throws KeeperException, InterruptedException {
        String path = "/" + groupName + "/" + memberName;
        String createdPath = zk.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("Created:" + createdPath);
    }

    public static void main(String[] args) throws KeeperException, InterruptedException, IOException {
        JoinGroup joinGroup = new JoinGroup();
        joinGroup.connect("mini1");
        joinGroup.join("fuckme", "lalala");
        Thread.sleep(Long.MAX_VALUE);
    }
}
