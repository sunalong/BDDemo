package com.itcode.hdfs.task;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Todo:logBean的WriteableComparable的实现
 * Todo:数据导入前需要去除第一行
 * Todo:先小批量导入测试
 * Todo:先MR再Hive再spark
 * 统计：
 * 某个玩家 （PRODUCT_ID,IDFA or MZTGAME_UDID) 在3个月内是否有登录过150次（按小时算，1小时登陆N次，只算1次计数）
 * 玩家设备判定：
 * (1) PRODUCT_ID,UID ,IDFA
 * (2) PRODUCT_ID,UID,MZTGAME_UDID
 * 满足这两个条件之一，都可以认为条件通过
 */
public class TaskMapper extends Mapper<LongWritable, Text, KeyBean, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) {
        try {
            String[] fieldArr = value.toString().split(",");
            String LOG_DATE = fieldArr[0].replace("\"", "");//时间,精确到小时;"2018-03-07 19:06:11"
            String PRODUCT_ID = fieldArr[1].replace("\"", "");
            String UID = fieldArr[2].replace("\"", "");
//        String ACCOUNT = fieldArr[3];
//        String CLIENT_IP = fieldArr[4];
            String IDFA = fieldArr[5].replace("\"", "");
//        String DEVICE_TYPE = fieldArr[6];
            String MZTGAME_UDID = fieldArr[7].replace("\"", "");
//        String geoip_real_region_name = fieldArr[8];

//        String time = fieldArr[0].substring(0,13);//时间,精确到小时，天，月
//        int conditionOneNum = 0;//按条件1计算出来的数据
//        int conditionTwoNum = 0;//按条件2计算出来的数据
//        int repetitionNum = 0;//条件1与条件2重合的数据
//        int diffNum = 0;//conditionOneNum + conditionTwoNum - repetitionNum；去除条件1与条件2生命的数据
//        LogBean logBean = new LogBean();
//       KeyBean(String PRODUCT_ID, String UID, String IDFA, String MZTGAME_UDID) {

            KeyBean keyBean = new KeyBean(PRODUCT_ID, UID, IDFA, MZTGAME_UDID);
//        System.out.println("pid:"+PRODUCT_ID+" uid:"+UID+" idfa:"+IDFA+" mudid:"+MZTGAME_UDID);
//        System.out.println(keyBean.toString());
            context.write(keyBean, new Text(LOG_DATE));
        } catch (Exception e) {
            System.out.println("--------key:" + key + " value:" + value.toString());
            e.printStackTrace();
        }
    }

}
