package com.itcode.mapreduce.partition;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.HashMap;

/**
 * Created by along on 17/6/7.
 */
public class ProvincePartitioner extends Partitioner<Text, FlowBean> {
    private static HashMap<String, Integer> provinceDict = new HashMap<>();

    static {
        provinceDict.put("136", 0);
        provinceDict.put("137", 1);
        provinceDict.put("138", 2);
        provinceDict.put("139", 3);
    }

    @Override
    public int getPartition(Text text, FlowBean flowBean, int numPartitions) {
        String substring = text.toString().substring(0,3);
        Integer provinceId = provinceDict.get(substring);
        return provinceId == null ? 4 : provinceId;
    }
}
