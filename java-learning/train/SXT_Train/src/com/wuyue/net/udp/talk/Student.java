package com.wuyue.net.udp.talk;

public class Student {
    public static void main(String[] args) {
        new Thread(new Send(20480, "localhost", 10240)).start();
        new Thread(new Receive(20481)).start();
    }
}
