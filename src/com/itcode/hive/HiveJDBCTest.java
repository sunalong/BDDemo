package com.itcode.hive;

import java.sql.*;

/**
 * Created by along on 17/9/19 15:41.
 * Email:466210864@qq.com
 * jdbc连接hive数据库，查询数据并打印
 */
public class HiveJDBCTest {
    public static void main(String[] args) throws Exception {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            String url ="jdbc:hive2://mini1:10000";
            Connection connection = DriverManager.getConnection(url, "hadoop", "");
            String sql = "select * from tb_sz01";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
                System.out.println(resultSet.getString(1) + "<->" + resultSet.getString(2));

    }
}
