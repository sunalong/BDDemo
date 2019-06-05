package com.itcode.elasticsearch.hdfs.bean;

import java.util.ArrayList;
import java.util.List;

public class DBBean {
    private String dbName;
    private List<TableBean> tableList = new ArrayList();

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public List<TableBean> getTableList() {
        return tableList;
    }

    public void setTableList(List<TableBean> tableList) {
        this.tableList = tableList;
    }

    @Override
    public String toString() {
        return "{\"dbName\":\"" + dbName + "\", \"tableList\":" + tableList +'}';
    }
}
