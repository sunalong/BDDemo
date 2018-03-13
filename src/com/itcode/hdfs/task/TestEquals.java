package com.itcode.hdfs.task;

public class TestEquals {
    public static void main(String[] args){
        String uid = "3D19A1D5-FCFD-424D-AC1B-DCF7034C0D4E";
        String pid = "3D19A1D5-FCFD-424D-AC1B-DCF7034C0D4E";

        KeyBean keyBean1 = new KeyBean("5051","910062085","4B5515FF-388C-4BEB-A686-4203C3856C0B","1tps7adb9kt322bakavue4c9flqd1i20f1oar0b7o0m3c01");
        KeyBean keyBean2 = new KeyBean("5051","910062085","4B5515FF-388C-4BEB-A686-4203C3856C0B","1tps7adb9kt322bakavue4c9flqd1i20f1oar0b7o0m3c01");
        boolean f = (uid==pid);
        boolean s = uid.equals(pid);
        boolean ke = (keyBean1==keyBean2);
        boolean ke2 = (keyBean1.equals(keyBean2));
        System.out.println("1:f:"+f+" s:"+s+" ke:"+ke+" ke2:"+ke2);
    }
}
