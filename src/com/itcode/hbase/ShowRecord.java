package com.itcode.hbase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueExcludeFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

/***
 * 从表student查询数据
 * 查询所有
 * 根据主键查询一行
 * 根据主键和列族查询列族/某一列
 *
 *
 * @author Administrator
 *
 */
public class ShowRecord {

    private static Configuration hbaseconfig = null;

    static {
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "*.*.*.*");
        conf.set("hbase.zookeeper.property.clientPort", "2181");

        hbaseconfig = HBaseConfiguration.create(conf);
    }

    public static void main(String[] args) {
        //注意字段查询区分大小写

        /***
         * 查询表student
         */
        //查询所有
        ShowRecord.showAllRecords("student");

        //根据主键rowKey查询一行数据
        //ShowRecord.showOneRecordByRowKey("student", "200977100709");

        //根据主键查询某行中的一列
        //ShowRecord.showOneRecordByRowKey_cloumn("student", "200977100709","name");
        //ShowRecord.showOneRecordByRowKey_cloumn("student", "200977100709","info:age");

    }

    /****
     * 使用scan查询所有数据
     * @param tableName
     */
    public static void showAllRecords(String tableName) {
        System.out.println("start==============show All Records=============");

        HTablePool pool = new HTablePool(hbaseconfig, 1000);
        //创建table对象
        HTable table = (HTable) pool.getTable(tableName);

        try {
            //Scan所有数据
            Scan scan = new Scan();
            ResultScanner rss = table.getScanner(scan);

            for (Result r : rss) {
                System.out.println("\n row: " + new String(r.getRow()));

                for (KeyValue kv : r.raw()) {

                    System.out.println("family=>" + new String(kv.getFamily(), "utf-8")
                            + "  value=>" + new String(kv.getValue(), "utf-8")
                            + "  qualifer=>" + new String(kv.getQualifier(), "utf-8")
                            + "  timestamp=>" + kv.getTimestamp());
                }
            }
            rss.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("\n end==============show All Records=============");
    }

    /***
     * 根据主键rowKey查询一行数据
     * get 'student','010'
     */
    public static void showOneRecordByRowKey(String tableName, String rowkey) {
        HTablePool pool = new HTablePool(hbaseconfig, 1000);
        HTable table = (HTable) pool.getTable(tableName);

        try {
            Get get = new Get(rowkey.getBytes()); //根据主键查询
            Result r = table.get(get);
            System.out.println("start===showOneRecordByRowKey==row: " + "\n");
            System.out.println("row: " + new String(r.getRow(), "utf-8"));

            for (KeyValue kv : r.raw()) {
                //时间戳转换成日期格式
                String timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(new Date(kv.getTimestamp()));
                //System.out.println("===:"+timestampFormat+"  ==timestamp: "+kv.getTimestamp());
                System.out.println("\nKeyValue: " + kv);
                System.out.println("key: " + kv.getKeyString());

                System.out.println("family=>" + new String(kv.getFamily(), "utf-8")
                        + "  value=>" + new String(kv.getValue(), "utf-8")
                        + "  qualifer=>" + new String(kv.getQualifier(), "utf-8")
                        + "  timestamp=>" + timestampFormat);

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("end===========showOneRecordByRowKey");
    }

    /**
     * 根据rowkey,一行中的某一列簇查询一条数据
     * get 'student','010','info'
     * student sid是010的info列簇（info:age,info:birthday）
     * <p>
     * get 'student','010','info:age'
     * student sid是010的info:age列,quafilier是age
     */
    //public static void showOneRecordByRowKey_cloumn(String tableName,String rowkey,String column,String quafilier)
    public static void showOneRecordByRowKey_cloumn(String tableName, String rowkey, String column) {
        System.out.println("start===根据主键查询某列簇showOneRecordByRowKey_cloumn");

        HTablePool pool = new HTablePool(hbaseconfig, 1000);
        HTable table = (HTable) pool.getTable(tableName);

        try {
            Get get = new Get(rowkey.getBytes());
            get.addFamily(column.getBytes()); //根据主键查询某列簇
            //get.addColumn(Bytes.toBytes(column),Bytes.toBytes(quafilier)); ////根据主键查询某列簇中的quafilier列
            Result r = table.get(get);

            for (KeyValue kv : r.raw()) {
                System.out.println("KeyValue---" + kv);
                System.out.println("row=>" + new String(kv.getRow()));
                System.out.println("family=>" + new String(kv.getFamily(), "utf-8") + ": " + new String(kv.getValue(), "utf-8"));
                System.out.println("qualifier=>" + new String(kv.getQualifier()) + "\n");

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("end===========showOneRecordByRowKey_cloumn");
    }

    //（1）时间戳到时间的转换.单一的时间戳无法给出直观的解释。
    public String GetTimeByStamp(String timestamp) {
        long datatime = Long.parseLong(timestamp);
        Date date = new Date(datatime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
        String timeresult = format.format(date);
        System.out.println("Time : " + timeresult);
        return timeresult;

    }

    //（2）时间到时间戳的转换。注意时间是字符串格式。字符串与时间的相互转换，此不赘述。
    public String GetStampByTime(String time) {
        String Stamp = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = sdf.parse(time);
            Stamp = date.getTime() + "000";
            System.out.println(Stamp);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Stamp;
    }

}