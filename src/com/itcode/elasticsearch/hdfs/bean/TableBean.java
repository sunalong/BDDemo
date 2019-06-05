package com.itcode.elasticsearch.hdfs.bean;

import java.util.ArrayList;
import java.util.List;

public class TableBean {
    private String tabName;
    private List<FieldsBean> columns = new ArrayList();

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public List<FieldsBean> getColumns() {
        return columns;
    }

    public void setColumns(List<FieldsBean> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "{\"tabName\":\"" + tabName + "\",\"columns\":" + columns +'}';
    }
}
