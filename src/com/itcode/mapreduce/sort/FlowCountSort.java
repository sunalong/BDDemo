package com.itcode.mapreduce.sort;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by along on 17/6/7.
 */
public class FlowCountSort {
    static class FlowCountSortMapper extends Mapper<LongWritable,Text,FlowBean,Text>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] fieldArr = value.toString().split("\t");
            String phoneNum = fieldArr[0];
            String upStream = fieldArr[1];
            String downStream = fieldArr[2];
            FlowBean flowBean = new FlowBean(Integer.valueOf(upStream),Integer.parseInt(downStream));
            context.write(flowBean,new Text(phoneNum));
        }
    }
    static class FlowCountSortReducer extends Reducer<FlowBean,Text,Text,FlowBean>{
        @Override
        protected void reduce(FlowBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            context.write(values.iterator().next(),key);
        }
    }
    public static void main(String [] args) throws IOException, ClassNotFoundException, InterruptedException {
        //初始化输入输出参数
        if(args==null||args.length<2){
            args = new String[2];
            args[0] = "/wordcountPhone/input";
            args[1] = "/wordcountPhone/output";
        }
        Job job = Job.getInstance();
        Path inputPath =new Path(args[0]);
        Path outputPath =new Path(args[1]);

        //job所在的路径
        job.setJarByClass(FlowCountSort.class);
        //job所调用的mapper和reducer业务类
        job.setMapperClass(FlowCountSortMapper.class);
        job.setReducerClass(FlowCountSortReducer.class);
        //job要处理的原始数据文件所在的目录
        //job输出数据文件所在的目录
        FileInputFormat.setInputPaths(job,inputPath);
        FileOutputFormat.setOutputPath(job,outputPath);
        //mapper输出的数据类型
        job.setMapOutputKeyClass(FlowBean.class);
        job.setMapOutputValueClass(Text.class);
        //最终输出的数据类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
        //job提交给yarn执行
        boolean completion = job.waitForCompletion(true);
        System.exit(completion?0:1);
    }
}
