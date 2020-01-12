package com.wuyue.net.udp.transObj;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Date;

/**
 * udp的发送端
 */
public class UdpCilent {
    public static void main(String[] args) throws IOException {
        DatagramSocket cilent = new DatagramSocket(20480);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(byteArrayOutputStream));
        oos.writeUTF("来打我啊");
        oos.writeBoolean(true);
        oos.writeDouble(3.1415926);
        oos.writeObject(new Date());
        oos.flush();
        byte[] sendMsg = byteArrayOutputStream.toByteArray();
        DatagramPacket datas = new DatagramPacket(sendMsg,
                0,
                sendMsg.length,
                new InetSocketAddress("localhost", 10240));
        cilent.send(datas);
        oos.close();
        cilent.close();
        System.out.println("发送完成！");
    }
}
