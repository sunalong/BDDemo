package com.itcode.testzookeeper;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.List;

/**
 * Created by along on 17/9/6 18:15.
 * Email:466210864@qq.com
 */
public class ListGroup extends ConnectionWatcher {
    /**
     * 打印给定组下的节点名
     * @param groupName
     */
    public void list(String groupName) {
        try {
            String path = "/" + groupName;
            List<String> children = zk.getChildren(path, false);
            if (children.isEmpty())
                System.out.printf("No memebers in group %s\n", groupName);
            for (String child : children)
                System.out.println(child);
        } catch (KeeperException.NoNodeException e) {
            System.out.printf("Group %s does not exist\n", groupName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(1);
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        ListGroup listGroup = new ListGroup();
        listGroup.connect("mini1");
        listGroup.list("");
        listGroup.close();
    }
}
