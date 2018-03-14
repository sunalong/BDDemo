package com.itcode.hdfs.task;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TaskReduce extends Reducer<KeyBean,Text,Text,Text>{

    @Override
    protected void reduce(KeyBean key, Iterable<Text> values, Context context) {
        try {
            Map<String,Integer> hoursMap = new HashMap<>();//最多24*31*3条数据
            Map<String,Integer> daysMap = new HashMap<>();
            Map<String,Integer> monthsMap = new HashMap<>();
            for(Text rawDate:values){//2018-03-07 19
                if(!hoursMap.containsKey(rawDate)){
                    hoursMap.put(rawDate.toString(),1);
                }
            }
            System.out.println("\n\n-------小时到天：-------------------");
            setMaps(hoursMap, daysMap,10);
            System.out.println("--------天到月：------------------");
            setMaps(daysMap, monthsMap,7);
            StringBuilder sb = new StringBuilder();

            Iterator<Map.Entry<String, Integer>> it = monthsMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Integer> entry = it.next();
                System.out.println(entry.getKey() + ":" + entry.getValue());
                sb.append(entry.getKey() + ":" + entry.getValue()).append("\t");
            }

            System.out.println(key.toString()+"<--->"+sb.toString());
            context.write(new Text(key.toString()),new Text(sb.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setMaps(Map<String, Integer> hoursMap, Map<String, Integer> daysMap,int index) {
        Iterator<Map.Entry<String, Integer>> it = hoursMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
            String daysStr = entry.getKey().substring(0,index);
            if(!daysMap.containsKey(daysStr)){
//                daysMap.put(daysStr,1);
                daysMap.put(daysStr,entry.getValue());//到月份时需要将当月每天累加的小时数加起来
            }else{
                daysMap.put(daysStr,daysMap.get(daysStr)+1);
            }
        }
    }
}
