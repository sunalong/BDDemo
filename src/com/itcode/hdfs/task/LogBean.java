package com.itcode.hdfs.task;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class LogBean implements WritableComparable<LogBean>{
    private String LOG_DATE;
    private String PRODUCT_ID;
    private String UID;
    private String ACCOUNT;
    private String CLIENT_IP;
    private String IDFA;
    private String DEVICE_TYPE;
    private String MZTGAME_UDID;
    private String geoip_real_region_name;

    private int byHourCount;//按小时统计的登录次数
    private int byDayCount;//按天统计的登录次数
    private int byMonthCount;//按月统计的登录次数
    private int byAllTimeCount;//按所有时间(3个月)统计的登录次数
    private int conditionOneNum;//按条件1计算出来的数据
    private int conditionTwoNum;//按条件2计算出来的数据
    private int repetitionNum;//条件1与条件2重合的数据
    private int diffNum;//conditionOneNum + conditionTwoNum - repetitionNum；去除条件1与条件2生命的数据

    public String getLOG_DATE() {
        return LOG_DATE;
    }

    public void setLOG_DATE(String LOG_DATE) {
        this.LOG_DATE = LOG_DATE;
    }

    public String getPRODUCT_ID() {
        return PRODUCT_ID;
    }

    public void setPRODUCT_ID(String PRODUCT_ID) {
        this.PRODUCT_ID = PRODUCT_ID;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getACCOUNT() {
        return ACCOUNT;
    }

    public void setACCOUNT(String ACCOUNT) {
        this.ACCOUNT = ACCOUNT;
    }

    public String getCLIENT_IP() {
        return CLIENT_IP;
    }

    public void setCLIENT_IP(String CLIENT_IP) {
        this.CLIENT_IP = CLIENT_IP;
    }

    public String getIDFA() {
        return IDFA;
    }

    public void setIDFA(String IDFA) {
        this.IDFA = IDFA;
    }

    public String getDEVICE_TYPE() {
        return DEVICE_TYPE;
    }

    public void setDEVICE_TYPE(String DEVICE_TYPE) {
        this.DEVICE_TYPE = DEVICE_TYPE;
    }

    public String getMZTGAME_UDID() {
        return MZTGAME_UDID;
    }

    public void setMZTGAME_UDID(String MZTGAME_UDID) {
        this.MZTGAME_UDID = MZTGAME_UDID;
    }

    public String getGeoip_real_region_name() {
        return geoip_real_region_name;
    }

    public void setGeoip_real_region_name(String geoip_real_region_name) {
        this.geoip_real_region_name = geoip_real_region_name;
    }

    public int getByHourCount() {
        return byHourCount;
    }

    public void setByHourCount(int byHourCount) {
        this.byHourCount = byHourCount;
    }

    public int getByDayCount() {
        return byDayCount;
    }

    public void setByDayCount(int byDayCount) {
        this.byDayCount = byDayCount;
    }

    public int getByMonthCount() {
        return byMonthCount;
    }

    public void setByMonthCount(int byMonthCount) {
        this.byMonthCount = byMonthCount;
    }

    public int getByAllTimeCount() {
        return byAllTimeCount;
    }

    public void setByAllTimeCount(int byAllTimeCount) {
        this.byAllTimeCount = byAllTimeCount;
    }

    public int getConditionOneNum() {
        return conditionOneNum;
    }

    public void setConditionOneNum(int conditionOneNum) {
        this.conditionOneNum = conditionOneNum;
    }

    public int getConditionTwoNum() {
        return conditionTwoNum;
    }

    public void setConditionTwoNum(int conditionTwoNum) {
        this.conditionTwoNum = conditionTwoNum;
    }

    public int getRepetitionNum() {
        return repetitionNum;
    }

    public void setRepetitionNum(int repetitionNum) {
        this.repetitionNum = repetitionNum;
    }

    public int getDiffNum() {
        return diffNum;
    }

    public void setDiffNum(int diffNum) {
        this.diffNum = diffNum;
    }

    @Override
    public int compareTo(LogBean o) {

        return 0;
    }

    @Override
    public void write(DataOutput out) throws IOException {

    }

    @Override
    public void readFields(DataInput in) throws IOException {

    }
}
