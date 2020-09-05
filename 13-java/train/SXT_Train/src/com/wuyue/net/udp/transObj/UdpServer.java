package com.wuyue.net.udp.transObj;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

/**
 * udp的接收端
 */
public class UdpServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DatagramSocket server = new DatagramSocket(10240);
        byte[] container = new byte[1024 * 64];
        DatagramPacket sendData = new DatagramPacket(container, 0, container.length);
        server.receive(sendData);
        byte[] receiveData = sendData.getData();
        int len = sendData.getLength();
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(receiveData)));
        String msg = ois.readUTF();
        boolean flag = ois.readBoolean();
        double num = ois.readDouble();
        Object obj = ois.readObject();
        Date date = null;
        if (obj instanceof Date)
            date = (Date) obj;
        System.out.println(msg + " " + flag + " " + num);
        System.out.println(date);
        ois.close();
        server.close();
    }
}
