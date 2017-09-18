package com.itcode.mapreduce.partition;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by along on 17/6/6.
 */
public class FlowBean implements Writable{
    private long upStream;
    private long downStream;
    private long totalStream;

    public FlowBean() {}

    public FlowBean(long upStream, long downStream) {
        this.upStream = upStream;
        this.downStream = downStream;
        this.totalStream = upStream + downStream;
    }

    public long getUpStream() {
        return upStream;
    }

    public void setUpStream(long upStream) {
        this.upStream = upStream;
    }

    public long getDownStream() {
        return downStream;
    }

    public void setDownStream(long downStream) {
        this.downStream = downStream;
    }

    public long getTotalStream() {
        return totalStream;
    }

    public void setTotalStream(long totalStream) {
        this.totalStream = totalStream;
    }

    @Override
    public String toString() {
    return upStream+"\t"+downStream+"\t"+totalStream;
    }

    /**
     * 序列化方法
     * @param dataOutput
     * @throws IOException
     */
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(upStream);
        dataOutput.writeLong(downStream);
        dataOutput.writeLong(totalStream);
    }

    /**
     * 反序列化方法
     * @param dataInput
     * @throws IOException
     */
    @Override
    public void readFields(DataInput dataInput) throws IOException {
        upStream = dataInput.readLong();
        downStream = dataInput.readLong();
        totalStream = dataInput.readLong();
    }
}
