package com.itcode.hdfs.task;

import com.itcode.mapreduce.combiner.WordCount;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;
import java.io.IOException;

public class TaskCount {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args == null || args.length < 2) {
//            args[0] = "hdfs://mini1:9000/wordcountJar/input";
//            args[1] = "hdfs://mini1:9000/wordcountJar/output";
            args = new String[2];
            args[0] = "file:///Users/along/ATest/input1";
            args[1] = "file:///Users/along/ATest/input2";
        }
        String rmOutpath = "rm -rf /Users/along/ATest/input2";
        Process ps = Runtime.getRuntime().exec(rmOutpath);
        ps.waitFor();
//      System.sh
        Path inputPath = new Path(args[0]);
        Path outputPath = new Path(args[1]);
//        //删除已有的输出目录
//        Configuration configuration = new Configuration();
//        configuration.set("fs.defaultFS", "hdfs://mini1:8020");
//        configuration.set("yarn.resourcemanager.hostname", "mini1");
//        System.setProperty("HADOOP_USER_NAME", "hadoop");
//        FileSystem fs = FileSystem.get(configuration);
//        fs.delete(outputPath, true);

        Job job = Job.getInstance();
        //指定本Jar包所在的路径
        job.setJarByClass(TaskCount.class);

        //指定本job要处理的原始数据文件所在的目录
        //指定本job输出的数据文件所在的目录
        FileInputFormat.addInputPath(job, inputPath);
        FileOutputFormat.setOutputPath(job, outputPath);
        job.setMapperClass(TaskMapper.class);
        job.setReducerClass(TaskReduce.class);

        //设置inputStream,默认使用TextInputFormat.class
//        job.setInputFormatClass(KeyValueTextInputFormat.class);//没有行号啊？

        //设置map和reduce的输入与输出数据类型
//        Mapper<LongWritable, Text, KeyBean, Text>
//        Reducer<KeyBean,Text,Text,Text>
        job.setMapOutputKeyClass(KeyBean.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        //将本jar提交给yarn去执行
        boolean completion = job.waitForCompletion(true);
        System.exit(completion ? 0 : 1);
    }
}
