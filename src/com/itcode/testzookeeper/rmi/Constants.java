package com.itcode.testzookeeper.rmi;

/**
 * Created by along on 17/9/12 17:19.
 * Email:466210864@qq.com
 */
public class Constants {
    public static final String ZK_CONNECTION_HOSTS = "mini1:2181,mini2:2181,mini3:2181";
    public static final int ZK_SESSION_TIMEOUT = 5000;
    public static final String ZK_REGISTRY_PATH = "/registry";
    public static final String ZK_PROVICER_PATH = ZK_REGISTRY_PATH + "/provider";
    public static final String ZK_LOCK_HOSTS = "mini1:2181";
    public static final String ZK_LOCK_GROUP_PATH = "/distributedlock";
}
