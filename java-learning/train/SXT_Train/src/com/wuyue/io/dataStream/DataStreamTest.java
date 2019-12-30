package com.wuyue.io.dataStream;

import java.io.*;

public class DataStreamTest {
    public static void main(String[] args) {
        try (DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("Test/data/Java.data")
                ));
             DataInputStream dis = new DataInputStream(
                     new BufferedInputStream(
                             new FileInputStream("Test/data/Java.data")
                     )
             )
        ) {
            dos.writeInt(18);
            dos.writeUTF("Boom");
            dos.writeBoolean(true);
            dos.flush();
            int age = dis.readInt();
            String name = dis.readUTF();
            boolean flag = dis.readBoolean();
            System.out.println(age + " " + name + " " + flag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
