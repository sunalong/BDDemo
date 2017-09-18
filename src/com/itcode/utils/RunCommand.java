package com.itcode.utils;

import java.io.*;

/**
 * Created by along on 17/6/9.
 * Java调用Linux命令
 */
public class RunCommand {
    public static void main(String[] args) throws IOException {
        Process process = Runtime.getRuntime().exec("ls -R /Users/along/ATest");
        InputStream inputStream = process.getInputStream();
        BufferedReader read = new BufferedReader(new InputStreamReader(inputStream));
        String result;
        while((result = read.readLine())!=null){
            System.out.println(result);
        }
    }
}
