package com.itcode.testzookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * Created by along on 16/12/17.
 */
public class TestZK {
    private static String connectString = "mini1:2181,mini2:2181,mini3:2181";
    private static int sessionTimeout = 2000;
    ZooKeeper zooKeeper;

    public void init(String path) throws Exception {
        zooKeeper = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event.getType() + " iniit--" + event.getPath());
                try {
                    zooKeeper.getChildren(path, true);
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
     * 判断某个节点是否存在，若存在，则打印其数据
     * @param path
     * @param watch
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void exists( String path, boolean watch) throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists(path, watch);
        System.out.print("getAversion:"+stat.getAversion()+
        "\ngetCtime:"+stat.getCtime()+
        "\ngetCversion:"+stat.getCversion()+
        "\ngetCzxid:"+stat.getCzxid()+
        "\ngetDataLength:"+stat.getDataLength()+
        "\ngetEphemeralOwner:"+stat.getEphemeralOwner()+
        "\ngetMtime:"+stat.getMtime()+
        "\ngetMzxid:"+stat.getMzxid()+
        "\ngetNumChildren:"+stat.getNumChildren()+
        "\ngetPzxid:"+stat.getPzxid()+
        "\ngetVersion:"+stat.getVersion()
        );
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

    /**
     * 获取指定节点的数据
     * @param path
     * @param watch
     * @param stat
     * @throws KeeperException
     * @throws InterruptedException
     */
 public void getData(String path, boolean watch, Stat stat) throws KeeperException, InterruptedException {
     byte[] data = zooKeeper.getData(path, watch, stat);
//     System.out.println(String.valueOf(data));
     System.out.println("fuckData:"+new String(data));
 }

    /**
     * 删除指定节点
     * @param path
     * @param version
     * @throws KeeperException
     * @throws InterruptedException
     */
 public void deleteZnode(String path, int version) throws KeeperException, InterruptedException {
     zooKeeper.delete(path,version);
 }

    /**
     * 设置指定节点的数据
     * @param path
     * @param data
     * @param version
     * @throws KeeperException
     * @throws InterruptedException
     */
 public void setData(String path, byte[] data, int version) throws KeeperException, InterruptedException {
     Stat stat = zooKeeper.setData(path, data, version);
     System.out.print("getAversion:"+stat.getAversion()+
             "\ngetCtime:"+stat.getCtime()+
             "\ngetCversion:"+stat.getCversion()+
             "\ngetCzxid:"+stat.getCzxid()+
             "\ngetDataLength:"+stat.getDataLength()+
             "\ngetEphemeralOwner:"+stat.getEphemeralOwner()+
             "\ngetMtime:"+stat.getMtime()+
             "\ngetMzxid:"+stat.getMzxid()+
             "\ngetNumChildren:"+stat.getNumChildren()+
             "\ngetPzxid:"+stat.getPzxid()+
             "\ngetVersion:"+stat.getVersion()
     );
 }
}

