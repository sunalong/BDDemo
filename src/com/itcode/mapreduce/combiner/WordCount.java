package com.itcode.mapreduce.combiner;

import com.itcode.utils.SplitWord;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.List;

/**
 * Created by along on 17/6/5.
 * 统计汉字/英文单词/标点符号出现的次数
 */
public class WordCount {
    /**
     * Created by along on 17/6/5.
     * KEYIN：mr读取每行文本的起始偏移量
     * VALUEIN：mr读取每行文本的内容
     * KEYOUT：用户处理内容后输出的key
     * VALUEOUT：用户处理内容后输出的value
     */
    static class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            List<String> stringList = SplitWord.splitWord(value.toString());

            for (String word : stringList) {
                context.write(new Text(word), new IntWritable(1));
            }
        }
    }

    /**
     * Created by along on 17/6/5.
     * KEYIN：map输出的key
     * VALUEIN：map输出的value
     * KEYOUT：mr最终输出的key
     * VALUEOUT：mr最终输出的value
     */
    static class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int counts=0;
            for (IntWritable intWritable : values) {
                counts+=intWritable.get();
            }
            context.write(key,new IntWritable(counts));
        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if(args==null||args.length<2){
//            args[0] = "hdfs://mini1:9000/wordcountJar/input";
//            args[1] = "hdfs://mini1:9000/wordcountJar/output";
            args = new String[2];
            args[0] = "file:///Users/along/ATest/input1";
            args[1] = "file:///Users/along/ATest/input2";
        }
        Path inputPath = new Path(args[0]);
        Path outputPath = new Path(args[1]);

        Job job = Job.getInstance();
        //指定本Jar包所在的路径
        job.setJarByClass(WordCount.class);

        //指定本job要处理的原始数据文件所在的目录
        //指定本job输出的数据文件所在的目录
        FileInputFormat.addInputPath(job, inputPath);
        FileOutputFormat.setOutputPath(job, outputPath);

        //指定本job的mapper和reducer要使用的业务类
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        //指定需要使用combiner,以及哪个类作用combiner的逻辑
        job.setCombinerClass(WordCountReducer.class);
        //设置inputStream,默认使用TextInputFormat.class
        job.setInputFormatClass(CombineTextInputFormat.class);
        CombineTextInputFormat.setMaxInputSplitSize(job,4*1024*1024);
        CombineTextInputFormat.setMinInputSplitSize(job,2*1024*1024);
        //设置map和reduce的输入与输出的数据类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //将本jar提交给yarn去执行
        boolean completion = job.waitForCompletion(true);
        System.exit(completion ? 0 : 1);
    }
}
