package com.itcode.testzookeeper;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by along on 17/9/8 17:46.
 * Email:466210864@qq.com
 */
public class ConfigUpdater {
    public static final String PATH = "/config";
    private Random random = new Random();
    private ActiveKeyValueStore store;

    public ConfigUpdater(String hosts) throws IOException, InterruptedException {
        store = new ActiveKeyValueStore();
        store.connect(hosts);
    }

    public void run() throws KeeperException, InterruptedException {
        while (true) {
            String value = String.valueOf(random.nextInt(10));
            store.write(PATH, value);
            System.out.printf("Set %s to %s \n", PATH, value);
            TimeUnit.SECONDS.sleep(random.nextInt(3));
        }
    }

    public static void main(String[] args) throws Exception {
        ConfigUpdater configUpdater = new ConfigUpdater("mini1");
        configUpdater.run();
    }
}
