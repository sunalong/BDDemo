package com.itcode.testzookeeper.rmi.rmiClient;

import com.itcode.testzookeeper.ConnectionWatcher;
import com.itcode.testzookeeper.rmi.Constants;
import com.itcode.testzookeeper.rmi.rmiServer.ServiceProvider;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by along on 17/9/12 17:45.
 * Email:466210864@qq.com
 */
public class ServiceConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceProvider.class);
    private CountDownLatch latch = new CountDownLatch(1);
    //用于保存最新的 rmi地址
    private volatile List<String> urlList = new ArrayList<>();

    public ServiceConsumer() {
        ConnectionWatcher connectionWatcher = new ConnectionWatcher();
        ZooKeeper zooKeeper = connectionWatcher.connect(Constants.ZK_CONNECTION_HOSTS);
        watchZNode(zooKeeper);
    }

    //观察 /registry 节点的所有子节点并更新 urlList成员变量
    private void watchZNode(final ZooKeeper zooKeeper) {
        try {
            List<String> nodeList = zooKeeper.getChildren(Constants.ZK_REGISTRY_PATH, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getType() == Event.EventType.NodeChildrenChanged)//当某一个server挂掉后，自动更新urlList
                        watchZNode(zooKeeper);
                }
            });
            List<String> dataList = new ArrayList<>();
            for (String node : nodeList) {
                //获取 /registry 的子节点中的数据
                byte[] data = zooKeeper.getData(Constants.ZK_REGISTRY_PATH + "/" + node, false, null);
                dataList.add(new String(data));
            }
            LOGGER.debug("node data:{}", dataList);
            System.out.println("最新的dataList:" + dataList);
            urlList = dataList;//更新最新的rmi地址
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    //查找 RMI 服务
    public Remote lookup() {
        String url = null;
        Remote remoteService = null;
        int size = urlList.size();
        if (size > 0) {
            url = urlList.get(ThreadLocalRandom.current().nextInt(size));
            System.out.println("fuck url:" + url);
            remoteService = lookupService(url);
        }
        return remoteService;
    }

    //查找RMI服务
    private Remote lookupService(String url) {
        Remote remoteService = null;
        try {
            remoteService = Naming.lookup(url);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
//        } catch (Exception e) {
            if (e instanceof java.rmi.ConnectException) {//注意不要引错包了
                //最简单的重试方法，确保不会抛出异常：若连接失败，则使用urlList中的第一个RMI地址来查找
                LOGGER.error("FuckConnectException -> url:{}", url);
                System.out.printf("FuckConnectException -> url:{} %s\n", url);
                if (urlList.size() != 0) {
                    url = urlList.get(0);
                    return lookupService(url);
                }
            }
            e.printStackTrace();
        }
        return remoteService;
    }

}
