package com.wuyue.net.tcp.login;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 10240);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please enter username:");
        String uName = br.readLine();
        System.out.print("Please enter password:");
        String uPwd = br.readLine();
        dos.writeUTF(uName + "&" + uPwd);
        dos.close();
        socket.close();
    }
}
