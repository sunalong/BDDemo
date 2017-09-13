package com.itcode.testzookeeper.rmi.rmiServer;

import com.itcode.testzookeeper.ConnectionWatcher;
import com.itcode.testzookeeper.rmi.Constants;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;

/**
 * Created by along on 17/9/12 17:04.
 * Email:466210864@qq.com
 */
public class ServiceProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceProvider.class);

    //根据host、port绑定rmi服务并创建znode
    public void rebindAndCreateZNode(Remote remote, String host, int port) {
        String url = rebindServer(remote, host, port);
        ConnectionWatcher connectionWatcher = new ConnectionWatcher();
        ZooKeeper zk = connectionWatcher.connect(Constants.ZK_CONNECTION_HOSTS);
        createNode(zk, url);
    }

    //把url作为data创建znode
    private void createNode(ZooKeeper zk, String url){
        if (zk == null) {
            LOGGER.error("zookeeper is null!");
            return;
        }
        byte[] data = url.getBytes();//将当前的url作为数据赋值给当前的znode,以便以后根据znode获取url
        String path = null;
        try {
            path = zk.create(Constants.ZK_PROVICER_PATH, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.debug("create zookeeper node({}=>{})", path, url);
    }

    //绑定 rmi 服务
    private String rebindServer(Remote remote, String host, int port) {
        String formatUrl = null;
        try {
            formatUrl = String.format("rmi://%s:%d/%s", host, port, remote.getClass().getName());
            LocateRegistry.createRegistry(port);
            Naming.rebind(formatUrl, remote);
            LOGGER.debug("publish rmi service(formatUrl:{})", formatUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formatUrl;
    }
}
