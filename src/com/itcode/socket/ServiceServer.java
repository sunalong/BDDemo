package com.itcode.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by along on 17/1/7 12:26.
 * socket 服务端代码
 */
public class ServiceServer {
    public static void main(String args[]) throws IOException {
//        Socket socket = new Socket();
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("localhost",8899));

        while (true){
            Socket socket = serverSocket.accept();//accept 是一个阻塞方法，会一直等待，直到客户端请求到来才会执行
            //开启线程处理耗时的业务方法
            new Thread( new ServerTaskHandler(socket)).start();
        }
    }
}
