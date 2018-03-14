package com.itcode.hdfs.task;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 玩家设备判定：
 * (1) PRODUCT_ID,UID ,IDFA
 * (2) PRODUCT_ID,UID,MZTGAME_UDID
 * 满足这两个条件之一，都可以认为条件通过
 */
public class KeyBean implements WritableComparable<KeyBean> {
    private String PRODUCT_ID;
    private String UID;
    private String IDFA;
    private String MZTGAME_UDID;

    public KeyBean() {
    }

    public KeyBean(String PRODUCT_ID, String UID, String IDFA, String MZTGAME_UDID) {
        this.PRODUCT_ID = PRODUCT_ID;
        this.UID = UID;
        this.IDFA = IDFA;
        this.MZTGAME_UDID = MZTGAME_UDID;
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

    public String getIDFA() {
        return IDFA;
    }

    public void setIDFA(String IDFA) {
        this.IDFA = IDFA;
    }

    public String getMZTGAME_UDID() {
        return MZTGAME_UDID;
    }

    public void setMZTGAME_UDID(String MZTGAME_UDID) {
        this.MZTGAME_UDID = MZTGAME_UDID;
    }

    /**
     * CompaeTo()方法的返回值有三种类型。
     * 负整数、0、正整数分别对应小于、等于、大于被比较对象。
     *
     * @param keyBean
     * @return
     */
    @Override
    public int compareTo(KeyBean keyBean) {
//        if(this.PRODUCT_ID==keyBean.getPRODUCT_ID() && this.UID == keyBean.getUID()){
//            if(this.IDFA == keyBean.getIDFA() || this.MZTGAME_UDID == keyBean.MZTGAME_UDID){
//                return 0;
//            }
//            return -1;
//        }

//        if (this.PRODUCT_ID.equals(keyBean.getPRODUCT_ID()) && this.UID.equals(keyBean.getUID())) {
//            if (this.IDFA.equals(keyBean.getIDFA()) || this.MZTGAME_UDID.equals(keyBean.getMZTGAME_UDID())) {
//                return 0;
//            }
//            return -1;
//        }
//        return -1;
        int pidRet = this.getPRODUCT_ID().compareTo(keyBean.getPRODUCT_ID());
        int uidRet = this.getUID().compareTo(keyBean.getUID());

//        if (pidRet == 0 && uidRet == 0) {
        if (pidRet == 0) {
            if (uidRet == 0) {
                int idfaRet = this.getIDFA().compareTo(keyBean.getIDFA());
                int udidRet = this.getMZTGAME_UDID().compareTo(keyBean.getMZTGAME_UDID());
                if (idfaRet == 0 || udidRet == 0) {
                    return 0;
                }
                return idfaRet == 0 ? udidRet : idfaRet;
            } else {
                return uidRet;
            }
        } else {
            return pidRet;
        }
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(PRODUCT_ID);
        out.writeUTF(UID);
        out.writeUTF(IDFA);
        out.writeUTF(MZTGAME_UDID);

    }

    @Override
    public void readFields(DataInput in) throws IOException {
        PRODUCT_ID = in.readUTF();
        UID = in.readUTF();
        IDFA = in.readUTF();
        MZTGAME_UDID = in.readUTF();
    }

    @Override
    public String toString() {
        return "KeyBean{" +
                "PRODUCT_ID='" + PRODUCT_ID + '\'' +
                ", UID='" + UID + '\'' +
                ", IDFA='" + IDFA + '\'' +
                ", MZTGAME_UDID='" + MZTGAME_UDID + '\'' +
                '}';
    }
}