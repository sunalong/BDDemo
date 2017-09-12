package com.itcode.testzookeeper.rmi.rmiClient;

import com.itcode.testzookeeper.rmi.IHelloService;
import com.itcode.testzookeeper.rmi.rmiServer.HelloServiceImpl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by along on 17/9/12 16:18.
 * Email:466210864@qq.com
 */
public class RmiClient {
    public static void main(String[] args)
            throws RemoteException, NotBoundException, MalformedURLException {
        String serverClass = HelloServiceImpl.class.getName();
        String url = "rmi://localhost:1099/" + serverClass;
        IHelloService iHelloService = (IHelloService) Naming.lookup(url);
        String result = iHelloService.sayHello(RmiClient.class.getName());
        System.out.println(result);
    }
}
