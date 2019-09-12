package com.itcode;


import java.io.*;

public class JavaExecShell {
    public static void main(String[] args) {
        String commandStr="bash -x /Users/along/Github/BDDemo/src/com/itcode/shell/simpleShell.sh lala.sql";
        String finalResult = exec(commandStr);
        System.out.println("finalResult:" + finalResult);
    }

    /**
     * 执行 shell 命令，并返回执行结果
     *
     * @param command shell 命令
     * @return 命令执行的结果
     */
    public static String exec(String command) {
        System.out.println("command:" + command);
        String returnString = "";
        Process pro = null;
        Runtime runTime = Runtime.getRuntime();
        if (runTime == null) {
            System.err.println("Create runtime false!");
        }
        try {
            pro = runTime.exec(command);
            BufferedReader input = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            PrintWriter output = new PrintWriter(new OutputStreamWriter(pro.getOutputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                returnString = returnString + line + "\n";
            }
            input.close();
            output.close();
            pro.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnString;
    }
}
