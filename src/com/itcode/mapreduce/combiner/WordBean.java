package com.itcode.mapreduce.combiner;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by along on 17/6/8.
 */
public class WordBean implements WritableComparable<WordBean> {
    private long counts;
    private String word;

    public WordBean() {
    }

    public WordBean(long counts, String word) {
        this.counts = counts;
        this.word = word;
    }

    public long getCounts() {
        return counts;
    }

    public void setCounts(long counts) {
        this.counts = counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public int compareTo(WordBean o) {
        return this.counts > o.getCounts() ? -1 : 1;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(counts);
        dataOutput.writeUTF(word);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.counts = dataInput.readLong();
        this.word = dataInput.readUTF();
    }

    @Override
    public String toString() {
        return ""+counts;
    }
}
