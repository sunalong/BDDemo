package com.itcode.check;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by along on 17/2/10 16:15.
 * 读取～/Giant 中的 weHave.txt 和 haveParams.txt 中的数据
 * 查找 weHave.txt中的数据在 haveParams.txt 是否存在，
 * 如果不存在，则打印出其在 weHave.txt中的位置和数据，如：
 * 如果第五行的 S808 在 haveParams.txt 不存在，则打印：5.S808
 */
public class Check {
    static String weHave = "/Users/along/Giant/weHave.txt";
    static String haveParams = "/Users/along/Giant/haveParams.txt";

    public static void main(String[] args) {
        List<DeviceDomain> weHaveList = txt2String(weHave);
        System.out.println("------------1----------------------");
        printList(weHaveList);
        List<DeviceDomain> haveParamsList = txt2String(haveParams);
        System.out.println("--------------2--------------------");
        printList(haveParamsList);
        System.out.println("---------------3-------------------");
        for (int i = 0; i < haveParamsList.size(); i++) {
            for (int j = 0; j < weHaveList.size(); j++) {
                if (weHaveList.get(j).getName().equals(haveParamsList.get(i).getName())) {
                    weHaveList.get(j).setHave(true);
                    System.out.println("存在：" + weHaveList.get(j));
                }
            }
        }

    }

    private static void printList(List<DeviceDomain> weHaveList) {
        for (int i = 0; i < weHaveList.size(); i++) {
            System.out.println(weHaveList.get(i));
        }
    }

    public static List<DeviceDomain> txt2String(String file) {
        List<DeviceDomain> contextList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String name = null;
            int number = 0;
            while ((name = br.readLine()) != null) {//使用readLine方法，一次读一行
                DeviceDomain deviceDomain = new DeviceDomain(false, name.trim(), ++number);
                contextList.add(deviceDomain);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contextList;
    }
}
