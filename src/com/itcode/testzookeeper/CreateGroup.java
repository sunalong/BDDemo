package com.itcode.testzookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;

import java.io.IOException;

/**
 * Created by along on 17/9/7 15:33.
 * Email:466210864@qq.com
 */
public class CreateGroup extends ConnectionWatcher {
    private void create(String groupName) throws KeeperException, InterruptedException {
        String path = "/" + groupName;
        if (zk.exists(path, false) == null)
            zk.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("Created:" + path);
    }

    public static void main(String[] args) throws Exception {
        CreateGroup createGroup = new CreateGroup();
        createGroup.connect("mini1");
        createGroup.create("fuckme");
        createGroup.close();
    }
}
