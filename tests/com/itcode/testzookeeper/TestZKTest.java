package com.itcode.testzookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

/**
 * Created by along on 16/12/18.
 */
public class TestZKTest {
    private TestZK testZK;
    @org.junit.Before
    public void setUp() throws Exception {
        testZK = new TestZK();
        testZK.init();
    }

    @org.junit.Test
    public void createZnode() throws Exception {
        String path = "/fuckIntellijZnode";
        String data = "the test data";
        testZK.createZnode(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @org.junit.Test
    public void getZnodeData() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }
}