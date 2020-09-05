package com.wuyue.net.tcp.multiLogin;

import com.wuyue.oop.Circle;

import java.io.*;
import java.net.Socket;

public class MultiClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("118.25.46.104", 10240);
        Client client = new Client(socket);
        client.send();
        client.receive();
        client.release();
    }

    private static class Client {
        DataOutputStream dos;
        DataInputStream dis;
        BufferedReader br;
        Socket socket;

        public Client(Socket socket) {
            this.socket = socket;
            try {
                dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                br = new BufferedReader(new InputStreamReader(System.in));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void send() {
            try {
                System.out.print("Please enter username:");
                String userName = br.readLine();
                System.out.print("Please enter password:");
                String userPwd = br.readLine();
                dos.writeUTF(userName + "&" + userPwd);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void receive() {
            try {
                String receiveString = dis.readUTF();
                System.out.println(receiveString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void release() {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
