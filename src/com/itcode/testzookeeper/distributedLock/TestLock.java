package com.itcode.testzookeeper.distributedLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * Created by along on 17/9/13 17:30.
 * Email:466210864@qq.com
 */
public class TestLock {
    private static final int THREAD_NUM = 3;
    private static final Logger LOG = LoggerFactory.getLogger(TestLock.class);
    public static CountDownLatch threadSemaphore = new CountDownLatch(THREAD_NUM);
//    private static int threadId = 0;
    public static void main(String[] args) {

        for (int i = 0; i < THREAD_NUM; i++) {
           final int threadId = i;
            new Thread() {
                @Override
                public void run() {
                    LockService lockService = new LockService();
//                    lockService.doService(iDoTemplete);
                    lockService.doService(new IDoTemplete() {
                        @Override
                        public boolean doSomething() {
                            LOG.info("开始修改一个文件。。。线程ID:"+threadId);
                            return true;
                        }
                    });
                }
            }.start();
        }

        try {
            threadSemaphore.await();
            LOG.info("所有线程运行结束！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static IDoTemplete iDoTemplete = new IDoTemplete() {
        @Override
        public boolean doSomething() {
//            LOG.info("我要修改一个文件。。。。"+threadId);
            return true;
        }
    };
}
