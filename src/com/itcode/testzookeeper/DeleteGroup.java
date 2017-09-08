package com.itcode.testzookeeper;

import org.apache.zookeeper.KeeperException;

import java.util.List;

/**
 * Created by along on 17/9/8 12:29.
 * Email:466210864@qq.com
 */
public class DeleteGroup extends ConnectionWatcher {
    public void delete(String groupName) throws Exception {
        String path = "/" + groupName;
        List<String> children = zk.getChildren(path, false);
        for (String child : children) {
            System.out.println("删除" + path + "下的child:" + child);
            zk.delete(path + "/" + child, -1);
        }
        System.out.println("删除" + path);
        zk.delete(path, -1);
    }

    public static void main(String[] args) throws Exception {
        DeleteGroup deleteGroup = new DeleteGroup();
        deleteGroup.connect("mini1");
        deleteGroup.delete("fuckme");
        deleteGroup.close();
    }
}
