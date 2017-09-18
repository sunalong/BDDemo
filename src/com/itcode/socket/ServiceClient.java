package com.itcode.socket;

import java.io.*;
import java.net.Socket;

/**
 * Created by along on 17/1/7 12:48.
 * 请求 socket 的客户端
 */
public class ServiceClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8899);
        //向 socket 中写入数据
        InputStream in = socket.getInputStream();

        OutputStream out = socket.getOutputStream();

        PrintWriter printWriter = new PrintWriter(out);
        printWriter.println("client请求的参数：\n"+System.currentTimeMillis()+"\nHello\nnimei");
        printWriter.flush();

        //从网络中读取发送到该 socket 中的输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuffer sb = new StringBuffer();
        String line = "";
        while ((line=br.readLine()) != null) {
            sb.append(line);
        }
        System.out.println("请求服务器后返回的结果："+sb);
        in.close();
        out.close();
        socket.close();
    }
}
