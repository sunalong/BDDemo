package com.itcode;

import java.sql.*;
//import org.apache.phoenix.jdbc.PhoenixDriver.*;
public class PhoenixTest {
    private static String driver = "org.apache.phoenix.jdbc.PhoenixDriver";

    public static void main(String[] args) throws SQLException {
        try {


//            org.apache.phoenix.jdbc.PhoenixDriver
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Statement stmt = null;
        ResultSet rs = null;
//注意事项：在哪台机器上执行，一定要在哪台机器上配置好ip和host，否则会报错 Can't get the locations
//        Connection con = DriverManager.getConnection("jdbc:phoenix:uhadoop-evi34htx-master1,uhadoop-evi34htx-master2,uhadoop-evi34htx-core1:2181");
//        Connection con = DriverManager.getConnection("jdbc:phoenix:10.9.184.152,10.9.11.15,10.9.150.126:2181:/hbase");
        Connection con = DriverManager.getConnection("jdbc:phoenix:10.9.184.152:2181:/hbase");
//        本机的hosts文件中必须有hbase集群的ip和主机名，否则会提示超时

        stmt = con.createStatement();
        String sql = "select * from \"test_table\"";
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.print("ROW:"+rs.getString("ROW"));
            System.out.println(",name:"+rs.getString("name"));
        }
        stmt.close();
        con.close();
    }
}



