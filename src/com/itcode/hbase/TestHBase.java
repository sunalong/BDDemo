package com.itcode.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by along on 17/9/21 10:30.
 * Email:466210864@qq.com
 */
public class TestHBase {
    Connection connection;
    public static TableName tableName = TableName.valueOf("phoneDetail");
    public Random random = new Random();

    @Before
    public void setup() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "mini1,mini2,mini3");
        connection = ConnectionFactory.createConnection(configuration);
    }

    @After
    public void after() throws IOException {
        if (connection != null)
            connection.close();
    }

    @Test
    public void create() throws IOException {
        Admin connectionAdmin = connection.getAdmin();
        if (connectionAdmin.tableExists(tableName)) {
            connectionAdmin.disableTable(tableName);
            connectionAdmin.deleteTable(tableName);
        }
        HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
        HColumnDescriptor hColumnDescriptor = new HColumnDescriptor("cf1".getBytes());
        hColumnDescriptor.setMaxVersions(5);
        hColumnDescriptor.setBlockCacheEnabled(true);
        hColumnDescriptor.setBlocksize(18 * 1000 * 100);
        hTableDescriptor.addFamily(hColumnDescriptor);
        connectionAdmin.createTable(hTableDescriptor);
    }

    @Test
    public void insert() throws IOException {
        Table table = connection.getTable(tableName);
        List<Put> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Put put = new Put(getRowKey("138").getBytes());
            put.addColumn("cf1".getBytes(), "address".getBytes(), "上海".getBytes());
            put.addColumn("cf1".getBytes(), "type".getBytes(), String.valueOf(random.nextInt(2)).getBytes());
            list.add(put);
        }
        table.put(list);
    }

    @Test
    public void find() throws IOException {
        Table table = connection.getTable(tableName);
        Scan scan = new Scan("138100000000".getBytes(), "138997974636".getBytes());
        ResultScanner scanner = table.getScanner(scan);
        Iterator<Result> iterator = scanner.iterator();
        while (iterator.hasNext()) {
            Result next = iterator.next();
            byte[] typeValue = next.getValue("cf1".getBytes(), "type".getBytes());
            byte[] addressValue = next.getValue("cf1".getBytes(), "address".getBytes());
            System.out.println(new String(typeValue, "UTF-8") + "<--->" + new String(addressValue, "UTF-8"));
        }
    }

    @Test
    public void find1() throws IOException {
        Table table = connection.getTable(tableName);
        Scan scan = new Scan();
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        PrefixFilter prefixFilter = new PrefixFilter("1389".getBytes());
        SingleColumnValueFilter singleColumnValueFilter = new SingleColumnValueFilter("cf1".getBytes(), "type".getBytes(), CompareFilter.CompareOp.EQUAL, "1".getBytes());
        //过滤器的顺序影响效率
        filterList.addFilter(prefixFilter);
        filterList.addFilter(singleColumnValueFilter);
        scan.setFilter(filterList);
        ResultScanner scanner = table.getScanner(scan);
        Iterator<Result> iterator = scanner.iterator();
        while (iterator.hasNext()) {
            Result next = iterator.next();
            byte[] value = next.getValue("cf1".getBytes(), "address".getBytes());
            System.out.println(new String(value, "UTF-8"));
        }
    }

    private String getRowKey(String pre) {
        return pre + phoneSuffix(100 * 1000 * 1000, 999999999);
    }

    int phoneSuffix(int origin, int bound) {
        int n = bound - origin;
        if (n > 0)
            return random.nextInt(n) + origin;
        else
            return bound;
    }
}

