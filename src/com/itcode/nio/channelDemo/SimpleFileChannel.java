package com.itcode.nio.channelDemo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by along on 17/5/25.
 * 简单的FileChannel示例
 */
public class SimpleFileChannel {
    public static void main(String[] args) throws IOException {
        readFile();
    }

    private static void readFile() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("/Users/along/GitHub/JavaAdvance/src/com/itcode/nio/channelDemo/SimpleFileChannel.java", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("\n=========Read " + bytesRead+"=============\n");
            buf.flip();

            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }
}
