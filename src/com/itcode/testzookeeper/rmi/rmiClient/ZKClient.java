package com.itcode.testzookeeper.rmi.rmiClient;

import com.itcode.testzookeeper.rmi.IHelloService;

import java.rmi.RemoteException;

/**
 * Created by along on 17/9/12 17:41.
 * Email:466210864@qq.com
 */
public class ZKClient {
    public static void main(String[] args) throws RemoteException, InterruptedException {
        ServiceConsumer serviceConsumer = new ServiceConsumer();
        while (true) {
            IHelloService iHelloService = (IHelloService) serviceConsumer.lookup();
            String result = iHelloService.sayHello(ZKClient.class.getSimpleName() + System.currentTimeMillis());
            System.out.println(result);
            Thread.sleep(1000);
        }
    }
}
