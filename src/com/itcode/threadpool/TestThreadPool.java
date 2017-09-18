package com.itcode.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by along on 16/12/20.
 */
public class TestThreadPool {

    public static void main(String[] args) throws Exception {
        Random random = new Random();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(availableProcessors);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(availableProcessors);

        Future<String> callableSubmitFuture;
        List<Future<?>> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            //FixedThreadPool 提交线程，runnable 没返回值，callable 有返回值
            callableSubmitFuture = fixedThreadPool.submit(new TaskCallable(9));
            //存储线程执行结果
            result.add(callableSubmitFuture);
        }
        int doneNum = 0;
        while (doneNum < result.size()) {

            for (int i = 0; i < result.size(); i++) {
//                if (result.get(i) == null){
//
//                    continue;
//                }
                if (result.get(i) != null) {
                    if (result.get(i).isDone()) {
                        System.out.println("done:线程返回future结果： " + result.get(i).get());
                        result.set(i, null);
                        doneNum++;
                    }
                }
            }
        }
        fixedThreadPool.shutdown();
    }
}
