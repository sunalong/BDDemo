package com.itcode.testzookeeper.distributedLock;

import com.itcode.testzookeeper.rmi.Constants;
import com.oracle.tools.packager.Log;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * Created by along on 17/9/14 10:37.
 * Email:466210864@qq.com
 */
public class DistributedLock {
    private ZooKeeper zooKeeper;
    private String currentNodePath;
    private final Logger LOG = LoggerFactory.getLogger(DistributedLock.class);
    private String CURRENT_THREAD_NAME = Thread.currentThread().getName();
    //用来观察紧挨着排在自己前面的子节点
    private Watcher watchBeforeNode = new NodeWatcher();
    private IDoTemplete iDoTemplete;

    public DistributedLock(ZooKeeper zooKeeper, IDoTemplete iDoTemplete) {
        this.zooKeeper = zooKeeper;
        this.iDoTemplete = iDoTemplete;
    }

    /**
     * 创建子节点所在的父节点
     *
     * @param zkLockGroupPath
     * @param data
     */
    public boolean createGroupPath(String zkLockGroupPath, String data) {
        try {//Todo:加锁
            if (zooKeeper.exists(zkLockGroupPath, false) == null) {
                String parentNodePath = zooKeeper.create(zkLockGroupPath, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                Log.info(CURRENT_THREAD_NAME + "创建组节点成功，path:" + parentNodePath + " data:" + data);
            }
        } catch (KeeperException | InterruptedException e) {
            if (e instanceof KeeperException.NodeExistsException) {
                LOG.info("组节点已经完成，" + CURRENT_THREAD_NAME + "不必再创建");
                return true;
            }
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断当前znode是否可执行
     * 现在规则是当前节点序号按升序排在第一即可执行
     *
     * @return
     */
    public boolean currentCanExec() {
        try {
            if (zooKeeper.exists(Constants.ZK_LOCK_SUB_PATH, null) == null) {
                currentNodePath = zooKeeper.create(Constants.ZK_LOCK_SUB_PATH, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
                LOG.info(CURRENT_THREAD_NAME + "创建子节点成功，路径是：" + currentNodePath);
            }
            return checkSelfOrder();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 检查节点是不是位列第一，如果不是，则使用watcher来监控紧挨着排在其前面的节点的状态
     *
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    private boolean checkSelfOrder() throws KeeperException, InterruptedException {
        List<String> childrenNode = null;
        childrenNode = zooKeeper.getChildren(Constants.ZK_LOCK_GROUP_PATH, false);
        Collections.sort(childrenNode);
        int currentNodeIndex = childrenNode.indexOf(currentNodePath.substring(Constants.ZK_LOCK_GROUP_PATH.length() + 1));
        switch (currentNodeIndex) {
            case -1:
                LOG.error(CURRENT_THREAD_NAME + "：本节点"+currentNodePath+"不存在了" );
                return false;
            case 0:
                LOG.info(CURRENT_THREAD_NAME + "：本节点"+ currentNodePath+"在各子节点中位于队首" );
                return true;
            default:
                String beforeMeNodePath = Constants.ZK_LOCK_GROUP_PATH + "/" + childrenNode.get(currentNodeIndex - 1);
                LOG.info(CURRENT_THREAD_NAME + "：所有子节点中，排在我前面的是：" + beforeMeNodePath);
                try {
                    zooKeeper.getData(beforeMeNodePath, this.watchBeforeNode, new Stat());//用来观察此节点的状态
                } catch (KeeperException | InterruptedException e) {
                    if (zooKeeper.exists(beforeMeNodePath, false) == null) {
                        LOG.info(CURRENT_THREAD_NAME + "：子节点中，排列我前面的节点：" + beforeMeNodePath + "意外失踪，此时再重新查看自己的位置");
                        return checkSelfOrder();
                    }
                    e.printStackTrace();
                }
                return false;
        }
    }

    /**
     * 释放本节点所点资源
     * 1.删除本节点，让监控本节点的watcher获取状态
     * 2.关闭本节点的zk连接
     */
    public void releaseDoneNode() {
        try {
            if(zooKeeper.exists(currentNodePath,false) == null){
                LOG.error(CURRENT_THREAD_NAME+":本节点由于其他原因（服务器上被手动删除）已经不存在了"+currentNodePath);
                return;
            }
            zooKeeper.delete(currentNodePath,-1);//删除本节点
            LOG.info(CURRENT_THREAD_NAME+":节点："+currentNodePath+"已经被删除");
            zooKeeper.close();//关闭本节点的zk连接
        } catch (KeeperException |InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 用以观察节点的状态
     */
    class NodeWatcher implements Watcher {
        private Logger LOG = LoggerFactory.getLogger(NodeWatcher.class);

        @Override
        public void process(WatchedEvent event) {
//        if(event.getType() == Event.EventType.NodeDeleted && event.getPath().equals())
            if (event.getType() == Event.EventType.NodeDeleted) {
                LOG.info(Thread.currentThread().getName() + ":观察的节点被删除");
                try {
                    if (checkSelfOrder()) {//如果此时本节点刚好位于第一，则可执行
                        doWork();
                    }
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doWork() {
        LOG.info(CURRENT_THREAD_NAME + ":获取锁成功（本节点位列第一），开始干活");
        boolean haveDone = iDoTemplete.doSomething();
        TestLock.threadSemaphore.countDown();
        if(haveDone)
            releaseDoneNode();

    }
}