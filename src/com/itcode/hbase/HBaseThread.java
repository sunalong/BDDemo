package com.itcode.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.sql.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangwq
 * @date 2018/6/30 20:58
 */
public class HBaseThread implements Runnable{
    org.apache.hadoop.hbase.client.Connection connection;
    public static TableName tableName = TableName.valueOf("out_unified_goods_info_str2");

    public static void main(String[] args) {
        String sqlCount = "select * -- count(*) \n" +
                "from \"out_unified_goods_info\"\n" +
                "WHERE \"ROW\" BETWEEN 1 AND 1000\n" +
                "LIMIT 10\n";//查询ontime数据量

        ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 1000, 500, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(   5));
        for(int i=0;i<600;i++){
            HBaseThread myTask = new HBaseThread(sqlCount);
            executor.execute(myTask);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
    private String sql ;
    public HBaseThread(String sql){
       this.sql=sql;
    }
    public  void exeSql(String sql){

        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "uhadoop-evi34htx-master1,uhadoop-evi34htx-master2,uhadoop-evi34htx-core1");
//        String address = "jdbc:phoenix:10.9.184.152:2181:/hbase";

        try {
            long begin = System.currentTimeMillis();
            connection = ConnectionFactory.createConnection(configuration);
            Table table = connection.getTable(tableName);
            Scan scan = new Scan("1".getBytes(), "101405".getBytes());

            ResultScanner scanner = table.getScanner(scan);
            Iterator<Result> iterator = scanner.iterator();

            long end = System.currentTimeMillis();
            System.out.println("执行（）耗时："+(end-begin)/1000 +"s");
            int i=0;
            while (iterator.hasNext()) {
                Result next = iterator.next();
//                int rowlength = Bytes.toShort(next.getRow()..getBuffer(), kv.getOffset() + KeyValue.ROW_OFFSET);
                i+=1;
//                byte[] idValue = next.getValue("cf1".getBytes(), "id".getBytes());
//                byte[] goodsIdValue = next.getValue("cf1".getBytes(), "goods_id".getBytes());
//                byte[] goodsNameValue = next.getValue("cf1".getBytes(), "goods_name".getBytes());
//                byte[] brandIDValue = next.getValue("cf1".getBytes(), "brand_id".getBytes());
//                System.out.println(new String(idValue, "UTF-8") + "<--->" + new String(goodsNameValue, "UTF-8"));
            }
            System.out.println("count:"+i+"\n\n");

        } catch (Exception e) {
            e.printStackTrace();
        }finally {//关闭连接
            try {
                if(connection!=null){
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    @Override
    public void run() {
        exeSql(this.sql);
    }
}
