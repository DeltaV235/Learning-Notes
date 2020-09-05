package com.wuyue.net.udp.talk;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Date;

/**
 * 循环发送字符串，直至发送“Bye”后停止
 *
 * @author DeltaV235
 */
public class Send implements Runnable {
    private DatagramSocket send;
    private InetSocketAddress dis;

    public Send(int port, String toHost, int toPort) {
        try {
            send = new DatagramSocket(port);
            dis = new InetSocketAddress(toHost, toPort);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.print("Enter your name: ");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ) {
            ByteArrayOutputStream bao;
            ObjectOutputStream oos;
            Packet packet = new Packet(br.readLine());
            do {
                packet.setMsg(br.readLine());
                packet.setSendDate(new Date());
                bao = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(new BufferedOutputStream(bao));
                oos.writeObject(packet);
                oos.flush();
                byte[] sendData = bao.toByteArray();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, dis);
                send.send(sendPacket);
            } while (!packet.getMsg().equals("bye"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


