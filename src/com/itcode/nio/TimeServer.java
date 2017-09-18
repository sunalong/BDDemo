package com.itcode.nio;

/**
 * Created by along on 17/5/24.
 */
public class TimeServer {
    public static void main(String[] args){
        int port = 8080;
        if ( args != null && args.length<0){
            port = Integer.valueOf(args[0]);
        }
        MultiTimeServer multiTimeServer = new MultiTimeServer(port);
        new Thread(multiTimeServer,"NIO-MutitimeServer --001").start();
    }
}
