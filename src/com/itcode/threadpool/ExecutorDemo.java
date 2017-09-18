package com.itcode.threadpool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by along on 16/12/20.
 * 列出并发包中的各种线程池
 */
public class ExecutorDemo {
    public static void main(String[] args){
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("cpu 个数："+availableProcessors);//8

        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(availableProcessors);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(availableProcessors);
        ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    }

}
