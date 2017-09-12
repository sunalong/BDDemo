package com.itcode.testzookeeper.rmi.rmiServer;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * Created by along on 17/9/12 16:32.
 * Email:466210864@qq.com
 */
public class RmiServer {
    public static void main(String[] args) throws Exception {
        int port = 1099;
        String url = "rmi://localhost:" + port + "/" + HelloServiceImpl.class.getName();
        LocateRegistry.createRegistry(port);
        Naming.rebind(url, new HelloServiceImpl());
//        Thread.sleep(Long.MAX_VALUE);//若HelloSserviceImpl仅实现接口 Serializable，则需要使用此句
    }
}
