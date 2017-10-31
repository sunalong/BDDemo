package com.itcode.mapreduce.weather;

import com.itcode.mapreduce.test.MyKey;
import com.itcode.mapreduce.test.RunJob;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by along on 2017/10/27 12:07.
 * Email:466210864@qq.com
 * 读取weather文件，输出每月最高的三个气温数值,按降序输出
 */
public class WeatherSortApp {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://mini1:8020");
        configuration.set("yarn.resourcemanager.hostname", "mini1");
        System.setProperty("HADOOP_USER_NAME", "hadoop");

        FileSystem fs = FileSystem.get(configuration);

        Job job = Job.getInstance(configuration);
        job.setJarByClass(RunJob.class);

        job.setJobName("weather");

        job.setMapperClass(WeatherMap.class);
        job.setReducerClass(WeatherReducer.class);
        job.setMapOutputKeyClass(DateTemp.class);
        job.setMapOutputValueClass(DoubleWritable.class);
        job.setSortComparatorClass(MySort.class);

        job.setNumReduceTasks(1);
        job.setInputFormatClass(KeyValueTextInputFormat.class);
        FileInputFormat.addInputPath(job, new Path("/input/weather"));
        Path outpath = new Path("/output/weather");
        if (fs.exists(outpath)) {
            fs.delete(outpath, true);
        }
        FileOutputFormat.setOutputPath(job, outpath);
        boolean b = job.waitForCompletion(true);
        if (b) {
            System.out.println("fuck success成功了");
        }

    }


    static class WeatherMap extends Mapper<Text, Text, DateTemp, DoubleWritable> {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        @Override
        protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
            try {
                Date date = sdf.parse(key.toString());

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(calendar.MONTH);
                int day = calendar.get(calendar.DAY_OF_MONTH);
                int hour = calendar.get(calendar.HOUR_OF_DAY);

                double tempValue = Double.parseDouble(value.toString().substring(0, value.toString().lastIndexOf("c")));
                DateTemp dateTemp = new DateTemp();
                dateTemp.setYear(year);
                dateTemp.setMonth(month+1);
                dateTemp.setDayOfMonth(day);
                dateTemp.setHour(hour);
                dateTemp.setTempValue(tempValue);
                context.write(dateTemp, new DoubleWritable(tempValue));

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    static class WeatherReducer extends Reducer<DateTemp, DoubleWritable, Text, NullWritable> {
        @Override
        protected void reduce(DateTemp key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
            int i=0;
            for (DoubleWritable dw : values) {
                i++;
                String resultStr = key.getYear() + "-" + key.getMonth() + "-"
                        + key.getDayOfMonth() + "\t" + key.getHour()
                        + "h:  " + dw.get()+"c";

                context.write(new Text(resultStr), NullWritable.get());
                if(i==3){
                    break;
                }
            }
        }
    }

}