package com.itcode;

import java.sql.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangwq
 * @date 2018/6/30 20:58
 */
public class PhoenixJDBC implements Runnable{
    public static void main(String[] args) {
        String sqlCount = "select * -- count(*) \n" +
                "from \"out_unified_goods_info\"\n" +
                "WHERE \"ROW\" BETWEEN 1 AND 1000\n" +
                "LIMIT 10\n";//查询ontime数据量

        ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 400, 400, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(   34));
        for(int i=0;i<900;i++){
            PhoenixJDBC myTask = new PhoenixJDBC(sqlCount);
            executor.execute(myTask);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
    private String sql ;
    public PhoenixJDBC(String sql){
       this.sql=sql;
    }
    public  void exeSql(String sql){
//        String address = "jdbc:clickhouse://10.9.60.2:60123/default?max_execution_time=70";
//        String address = "jdbc:clickhouse://10.9.60.2:8123/default?socket_timeout=4000000";
        String address = "jdbc:phoenix:10.9.184.152:2181:/hbase";
        Connection connection = null;
        Statement statement = null;
        ResultSet results = null;
        try {
            Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
            Properties pros = new Properties();
//            pros.setProperty("max_execution_time","50000");
//            pros.setProperty("socket_timeout","4000000");
//            connection = DriverManager.getConnection(address,pros);
            connection = DriverManager.getConnection(address);
            statement = connection.createStatement();

            long begin = System.currentTimeMillis();
            results = statement.executeQuery(sql);
            long end = System.currentTimeMillis();
            System.out.println("执行（）耗时："+(end-begin)/1000 +"s");
            ResultSetMetaData rsmd = results.getMetaData();
            List<Map> list = new ArrayList();
            while(results.next()){
                Map map = new HashMap();
                for(int i = 1;i<=rsmd.getColumnCount();i++){
                    map.put(rsmd.getColumnName(i),results.getString(rsmd.getColumnName(i)));
                }
                list.add(map);
            }
            for(Map map : list){
                System.err.println(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {//关闭连接
            try {
                if(results!=null){
                    results.close();
                }
                if(statement!=null){
                    statement.close();
                }
                if(connection!=null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

//    @Override
    public void run() {
        exeSql(this.sql);
    }
}
