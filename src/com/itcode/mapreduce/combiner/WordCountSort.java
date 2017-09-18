package com.itcode.mapreduce.combiner;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by along on 17/6/8.
 * 将WordCount的统计结果按序输出
 */
public class WordCountSort {

    static class WordCountMapper extends Mapper<LongWritable, Text, WordBean, Text> {
        @Override
        protected void map(LongWritable key, Text value, Context context) {
            try {
                String[] splitArr = value.toString().split("\t");
                String word = splitArr[0];
                System.out.println(splitArr[0]+"===="+splitArr[1]+"End");
                int counts = Integer.parseInt(splitArr[1]);
                WordBean wordBean = new WordBean(counts,word);
                context.write(wordBean, new Text(word));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class WordCountReducer extends Reducer<WordBean, Text, Text, WordBean> {
        @Override
        protected void reduce(WordBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
//            Text fuckMe;
//            fuckMe = values.iterator().next();
//            System.out.println(fuckMe.toString() + "-------" + key);
//            context.write(fuckMe, key);
            context.write(values.iterator().next(), key);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args == null || args.length < 2) {
            args = new String[2];
//            args[0] = "hdfs://sunalongMBP:9000/wcsort/input";
//            args[1] = "hdfs://sunalongMBP:9000/wcsort/output";
            args[0] = "file:///Users/along/ATest/input2";
            args[1] = "file:///Users/along/ATest/outputFinial";
        }
        Path inputPath = new Path(args[0]);
        Path outputPath = new Path(args[1]);

        Job job = Job.getInstance();
        //指定本Jar包所在的路径
        job.setJarByClass(WordCountSort.class);

        //指定本job要处理的原始数据文件所在的目录
        //指定本job输出的数据文件所在的目录
        FileInputFormat.addInputPath(job, inputPath);
        FileOutputFormat.setOutputPath(job, outputPath);

        //指定本job的mapper和reducer要使用的业务类
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        //为何使用combiner会有错误？
//        //指定需要使用combiner,以及哪个类作用combiner的逻辑
//        job.setCombinerClass(WordCountReducer.class);
//        //设置inputStream,默认使用TextInputFormat.class
//        job.setInputFormatClass(CombineTextInputFormat.class);
//        CombineTextInputFormat.setMaxInputSplitSize(job, 4 * 1024 * 1024);
//        CombineTextInputFormat.setMinInputSplitSize(job, 2 * 1024 * 1024);
        //设置map和reduce的输入与输出的数据类型
        job.setMapOutputKeyClass(WordBean.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(WordBean.class);
        //将本jar提交给yarn去执行
        boolean completion = job.waitForCompletion(true);
        System.exit(completion ? 0 : 1);
    }
}
