package com.itcode.mapreduce.weather;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by along on 2017/10/27 12:19.
 * Email:466210864@qq.com
 * 日期、温度对应beans
 */
public class DateTemp implements WritableComparable<DateTemp> {
    private int year;
    private int month;
    private int dayOfMonth;
    private int hour;
    private double tempValue;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public double getTempValue() {
        return tempValue;
    }

    public void setTempValue(double tempValue) {
        this.tempValue = tempValue;
    }

    @Override
    public String toString() {
        return "DateTemp{" +
                "year=" + year +
                ", month=" + month +
//                ", dayOfMonth=" + dayOfMonth +
//                ", hour=" + hour +
                ", tempValue=" + tempValue +
                '}';
    }

    public int compareTo(DateTemp dateTemp) {
        int yearR = Integer.compare(this.year, dateTemp.getYear());
        if (yearR == 0) {
            int monthR = Integer.compare(this.month, dateTemp.getMonth());
            if (monthR == 0) {
                return Double.compare(this.tempValue, dateTemp.getTempValue());
            }else{
                return monthR;
            }
        }else{
            return yearR;
        }
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(year);
        dataOutput.writeInt(month);
        dataOutput.writeInt(dayOfMonth);
        dataOutput.writeInt(hour);
        dataOutput.writeDouble(tempValue);
    }

    public void readFields(DataInput dataInput) throws IOException {
        year = dataInput.readInt();
        month = dataInput.readInt();
        dayOfMonth = dataInput.readInt();
        hour = dataInput.readInt();
        tempValue = dataInput.readDouble();
    }
}
