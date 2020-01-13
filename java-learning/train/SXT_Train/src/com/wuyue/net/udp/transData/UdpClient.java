package com.wuyue.net.udp.transData;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * udp的发送端
 */
public class UdpClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket client = new DatagramSocket(20480);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(byteArrayOutputStream));
        dataOutputStream.writeUTF("来打我啊");
        dataOutputStream.writeBoolean(true);
        dataOutputStream.writeDouble(3.1415926);
        dataOutputStream.flush();
        byte[] sendMsg = byteArrayOutputStream.toByteArray();
        DatagramPacket datas = new DatagramPacket(sendMsg,
                0,
                sendMsg.length,
                new InetSocketAddress("localhost", 10240));
        client.send(datas);
        dataOutputStream.close();
        client.close();
        System.out.println("发送完成！");
    }
}
