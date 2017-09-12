package com.itcode.testzookeeper.rmi.rmiServer;

import com.itcode.testzookeeper.rmi.IHelloService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 这个类需要被序列化，
 * 如果不继承 UnicastRemoteObject就需要实现 Serializable接口
 * 如果使用接口 Serializable，则RmiServer就需要手动阻塞
 */
public class HelloServiceImpl extends UnicastRemoteObject implements IHelloService {
    //public class HelloServiceImpl implements IHelloService,Serializable{//同上
    protected HelloServiceImpl() throws RemoteException {
    }

    @Override
    public String sayHello(String name) {
        return String.format("Hello %s", name);
    }
}
