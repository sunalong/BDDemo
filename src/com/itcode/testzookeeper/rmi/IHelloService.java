package com.itcode.testzookeeper.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by along on 17/9/12 16:21.
 * Email:466210864@qq.com
 */
public interface IHelloService extends Remote {
    /**
     * All of the methods on a RMI Remote interface
     * must declare RemoteException in their throws clause
     */
    String sayHello(String name) throws RemoteException;
}
