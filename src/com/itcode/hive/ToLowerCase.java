package com.itcode.hive;

import org.apache.hadoop.hive.ql.exec.UDF;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by along on 17/6/24.
 */
public class ToLowerCase extends UDF {
    private static Map<String, String> provinceMap = new HashMap<>();

    static {
        provinceMap.put("136", "北京");
        provinceMap.put("137", "上海");
        provinceMap.put("138", "杭州");
    }

    public String evaluate(int phoneNum) {
        String phonePrex = String.valueOf(phoneNum).substring(0, 3);
        String gsd = provinceMap.get(phonePrex);
        return gsd == null ? "火星" : gsd;
    }

    public String evaluate(String rawStr) {
        String result = rawStr.toLowerCase();
        return result;
    }
    public static void main(String[] args){
        ToLowerCase toLowerCase = new ToLowerCase();
        String evaluate = toLowerCase.evaluate(1378889);
        System.out.println(evaluate);
    }
}
