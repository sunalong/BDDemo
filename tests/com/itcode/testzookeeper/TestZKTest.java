package com.itcode.testzookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.junit.Test;

/**
 * Created by along on 16/12/18.
 */
public class TestZKTest {

    private TestZK testZK;

    @org.junit.Before
    public void setUp() throws Exception {
        testZK = new TestZK();
        testZK.init(path);
    }

    String path = "/TodayZnode";
    String data = "the test data";

    @org.junit.Test
    public void createZnode() throws Exception {
        testZK.createZnode(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @org.junit.Test
    public void getZnodeData() throws Exception {
        testZK.getZnodeData(path, true);
    }

    @Test
    public void createZnode1() throws Exception {
        testZK.createZnode(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @Test
    public void exists() throws Exception {
        testZK.exists(path, true);
    }

    @Test
    public void getZnodeData1() throws Exception {
        testZK.getZnodeData(path, true);
    }

    @Test
    public void getData() throws Exception {
        testZK.getData(path, true, null);
    }

    @Test
    public void deleteZnode() throws Exception {
        testZK.deleteZnode(path, -1);
    }

    @Test
    public void setData() throws Exception {
        testZK.setData(path, data.getBytes(), -1);
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }
}