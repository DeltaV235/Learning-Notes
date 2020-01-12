package com.wuyue.net.udp.transFile;

import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * udp的发送端
 */
public class UdpCilent {
    public static void main(String[] args) throws IOException {
        DatagramSocket cilent = new DatagramSocket(20480);
        byte[] sendMsg = FileUtils.readFileToByteArray(new File("Test/baidu.html"));
        DatagramPacket datas = new DatagramPacket(sendMsg,
                0,
                sendMsg.length,
                new InetSocketAddress("localhost", 10240));
        cilent.send(datas);
        cilent.close();
        System.out.println("发送完成！");
    }
}
