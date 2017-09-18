package com.itcode.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by along on 17/1/7 12:33.
 * 服务端的业务处理类
 */
public class ServerTaskHandler implements Runnable {
    private Socket socket;
    public ServerTaskHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            InetAddress inetAddress = socket.getInetAddress();

            //从网络中读取发送到该 socket 中的输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuffer sb = new StringBuffer();
//            String line = "";
//            while ((line=br.readLine()) != null) {
//                sb.append(line);
//            }
            sb.append(br.readLine());
            //根据读取输入到 socket 中的数据，返回相应的数据
            String retData = "请求的地址：getHostAddress:"+inetAddress.getHostAddress()
                    +" addresss:"+inetAddress.getAddress()
                    +" getCanonicalHostName:"+inetAddress.getCanonicalHostName()
                    +" getHostName:"+inetAddress.getHostName()
                    +"\n请求的参数："+sb.toString();
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(out));
//            PrintWriter printWriter = new PrintWriter(out);
            printWriter.println(retData);
            printWriter.flush();

            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
