package com.itcode.mapreduce.weather;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * Created by along on 2017/10/27 15:43.
 * Email:466210864@qq.com
 */
public class MySort extends WritableComparator{
    public MySort() {
        super(DateTemp.class,true);
    }

    /**
     * 注意是参数是 WritableComparable 而不是Object
     * @param a
     * @param b
     * @return
     */
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        DateTemp dateTemp1 = (DateTemp)a;
        DateTemp dateTemp2 = (DateTemp)b;

        int yearR = Integer.compare(dateTemp1.getYear(), dateTemp2.getYear());
        if (yearR == 0) {
            int monthR = Integer.compare(dateTemp1.getMonth(), dateTemp2.getMonth());
            if (monthR == 0) {
                return -Double.compare(dateTemp1.getTempValue(), dateTemp2.getTempValue());
            }else{
                return monthR;
            }
        }else{
            return yearR;
        }
    }
}
