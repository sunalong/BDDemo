package com.itcode;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by along on 17/9/18 11:21.
 * Email:466210864@qq.com
 */
public class TestJava {

    public static void main(String[] args) {
        System.out.println(true + "<--->" + false);
        try {
//            String shpath="/home/felven/word2vec/demo-classes.sh";
//            Process ps = Runtime.getRuntime().exec(shpath);
            String shcmd = "echo hello shell!";
            Process ps = Runtime.getRuntime().exec(shcmd);
            ps.waitFor();

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();
            System.out.println(result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
