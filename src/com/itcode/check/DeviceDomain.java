package com.itcode.check;

/**
 * Created by along on 17/2/10 16:34.
 */
public class DeviceDomain {
    private boolean have;//是否存在于
    private String name;//设备名称
    private int number;//第几行

    public DeviceDomain(boolean have, String name, int number) {
        this.have = have;
        this.name = name;
        this.number = number;
    }

    public boolean isHave() {
        return have;
    }

    public void setHave(boolean have) {
        this.have = have;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "DeviceDomain{" +
                "number=" + number +
                " have=" + have +
                " name='" + name + '\'' +
                '}';
    }
}
