package com.itcode.nio;


/**
 * Created by along on 17/5/31.
 */
public class TimeClient {
    public static void main(String[] args){
        int port = 8080;
        if(args !=null && args.length>0){
           port = Integer.valueOf(args[0]);
        }
        new Thread(new TimeClientHandle("127.0.0.1",port),"NIO-TimeClient-001").start();
    }
}
