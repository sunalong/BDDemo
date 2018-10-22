package com.itcode.hive;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;

import java.security.MessageDigest;

public class MD5 extends UDF {

    public String evaluate (final String str) {
        if (StringUtils.isBlank(str)){
            return "";
        }
        String digest = null;
        StringBuffer buffer = new StringBuffer();
        try {
            MessageDigest digester = MessageDigest.getInstance("md5");
            byte[] digestArray = digester.digest(str.getBytes("UTF-8"));
            for (int i = 0; i < digestArray.length; i++) {
                buffer.append(String.format("%02x", digestArray[i]));
            }
            digest = buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return digest;
    }
    public static void main (String[] args ) {
        MD5 md5 = new MD5();
        System.out.println(md5.evaluate("   "));
        System.out.println("fuck:"+md5.evaluate("fuck"));
        System.out.println("fck2:"+md5.evaluate("fu2324ck"));
    }
}