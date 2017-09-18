package com.itcode.mapreduce.customWritable;

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
 * Created by along on 17/6/6.
 * 自定义writable,用于统计上行\下行\总流量
 */
public class FlowCount {
    static class FlowCountMapper extends Mapper<LongWritable, Text, Text, FlowBean> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] fieldArray = value.toString().split("\t");
            String phoneName = fieldArray[1];
            int length = fieldArray.length;
            long up = Long.valueOf(fieldArray[length - 3]);
            long down = Long.parseLong(fieldArray[length - 2]);
            FlowBean flowBean = new FlowBean(up, down);
            context.write(new Text(phoneName), flowBean);
        }
    }

    static class FlowCountReducer extends Reducer<Text, FlowBean, Text, Text> {
        @Override
        protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
            long sumUp = 0;
            long sumDown = 0;
            for (FlowBean flowBean : values) {
                sumUp += flowBean.getUpStream();
                sumDown += flowBean.getDownStream();
            }
            FlowBean flowBeanSum = new FlowBean(sumUp, sumDown);
            context.write(key, new Text(flowBeanSum.toString()));
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            super.cleanup(context);
        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
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
        job.setJarByClass(FlowCount.class);
        //job所调用的mapper和reducer业务类
        job.setMapperClass(FlowCountMapper.class);
        job.setReducerClass(FlowCountReducer.class);
        //job要处理的原始数据文件所在的目录
        //job输出数据文件所在的目录
        FileInputFormat.setInputPaths(job,inputPath);
        FileOutputFormat.setOutputPath(job,outputPath);
        //mapper输出的数据类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);
        //最终输出的数据类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        //job提交给yarn执行
        boolean completion = job.waitForCompletion(true);
        System.exit(completion?0:1);
    }
}






















