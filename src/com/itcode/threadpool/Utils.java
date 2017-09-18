package com.itcode.threadpool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by along on 16/12/20.
 */
public class Utils {
    private static SimpleDateFormat simpleDateFormat;
    private static String pattern = "yyyy mm dd HH MM ss";
    private static Random random;

    /**
     * 获取格式化后的当前时间
     * @return
     */
    public static String getCurrentTime() {
        if (simpleDateFormat == null)
            simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获取随机数
     * @param bound
     * @return
     */
    public static int getRandomInt(int bound) {
        if (random == null)
            random = new Random();
        return random.nextInt(bound);
    }
}
