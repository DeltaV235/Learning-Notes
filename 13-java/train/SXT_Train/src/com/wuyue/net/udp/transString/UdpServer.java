package com.wuyue.net.udp.transString;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * udp的接收端
 */
public class UdpServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket server = new DatagramSocket(10240);
        byte[] container = new byte[1024 * 64];
        DatagramPacket sendData = new DatagramPacket(container, 0, container.length);
        server.receive(sendData);
        byte[] receiveData = sendData.getData();
        int len = sendData.getLength();
        System.out.println(new String(receiveData, 0, len));
        server.close();
    }
}
