package com.wuyue.net.udp.talk;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 循环接收字节数组，并转换成字符串，输出在stdout中
 *
 * @author DeltaV235
 */
public class Receive implements Runnable {
    private DatagramSocket receive;
    private DatagramPacket receivePacket;
    private final static int CONTAINER_SIZE = 1024 * 64;

    public Receive(int port) {
        try {
            receive = new DatagramSocket(port);
            receivePacket = new DatagramPacket(new byte[CONTAINER_SIZE], CONTAINER_SIZE);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                receive.receive(receivePacket);
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream
                        (new ByteArrayInputStream(receivePacket.getData())));
                Object object = ois.readObject();
                if (object instanceof Packet) {
                    Packet packet = (Packet) object;
                    Date sendDate = packet.getSendDate();
                    String senderName = packet.getSenderName();
                    String msg = packet.getMsg();
                    int dataLen = receivePacket.getLength();
                    System.out.println(new SimpleDateFormat("HH:mm:ss").format(sendDate) + "\t" + senderName
                            + ":" + msg + "\t\tData Length:" + dataLen + " Byte");
                    if (msg.equals("bye"))
                        break;
                } else
                    System.out.println("Lost Packet");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
