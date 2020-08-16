package com.wuyue.net.udp.talk;

public class Teacher {
    public static void main(String[] args) {
        new Thread(new Receive(10240)).start();
        new Thread(new Send(10241, "localhost", 20481)).start();
    }
}
