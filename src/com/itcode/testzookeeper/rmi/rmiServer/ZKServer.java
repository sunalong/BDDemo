package com.itcode.testzookeeper.rmi.rmiServer;

import com.itcode.testzookeeper.rmi.IHelloService;

/**
 * Created by along on 17/9/12 17:02.
 * Email:466210864@qq.com
 */
public class ZKServer {
    public static void main(String[] args) throws Exception {
        String host = "sunalongMBP";
        int port = 11231;
        ServiceProvider serviceProvider = new ServiceProvider();
        IHelloService iHelloService = new HelloServiceImpl();
        serviceProvider.rebindAndCreateZNode(iHelloService, host, port);
    }
}
