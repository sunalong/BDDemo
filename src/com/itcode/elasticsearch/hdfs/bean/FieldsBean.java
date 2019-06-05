package com.itcode.elasticsearch.hdfs.bean;

/**
 * HDFS fields java bean
 */
public class FieldsBean {
    private String fieldsName;
    private String type;
    private String Comment;

    public String getFieldsName() {
        return fieldsName;
    }

    public void setFieldsName(String fieldsName) {
        this.fieldsName = fieldsName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    @Override
    public String toString() {
        return "{\"fieldsName\":\"" + fieldsName + "\", \"type\":\"" + type + "\",\"Comment\":\"" + Comment + "\"}" ;
    }
}
