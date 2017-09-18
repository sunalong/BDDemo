package com.itcode.threadpool;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

/**
 * Created by along on 16/12/20.
 */
public class TaskCallable implements Callable<String> {
    private int value;

    public TaskCallable(int value) {
        this.value = value;
    }

    @Override
    public String call() throws Exception {
        String name = Thread.currentThread().getName();
        System.out.println(name + " 启动时间：" + Utils.getCurrentTime());
        Thread.sleep(Utils.getRandomInt(3) * 1000);
//        System.out.println(name + " is running,value:" + value);

        return name + "<-->" + value;
    }
}
