package com.itcode.hdfs.task;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ValuesBean implements Writable {
    /**
     * 每小时统计的次数
     */
    private int hoursCount;
    /**
     * 每天统计的次数
     */
    private int dayCount;
    /**
     * 每月统计的次数
     */
    private int monthCount;

    @Override
    public void write(DataOutput out) throws IOException {

    }

    @Override
    public void readFields(DataInput in) throws IOException {

    }
}
