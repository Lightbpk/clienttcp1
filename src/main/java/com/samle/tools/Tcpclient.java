package com.samle.tools;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Tcpclient {
    private Socket socket;
    private String msg;
    private String user;

    public void init() {
        try {
            System.out.println("Enter IP");
            //Scanner scanner = new Scanner(System.in);
            String host = "127.0.0.1";//scanner.next();
            System.out.println("Enter port");
            int port = 9000;//scanner.nextInt();
            socket = new Socket(host, port);
            System.out.println("successful creating socket");
            user = "User"+Math.random();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("err creating socket");
        }

    }

    public void run() {
        try {
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream out = new DataOutputStream(outputStream);
            boolean running = true;
            while (running){
                Scanner scanner = new Scanner(System.in);
                String s = scanner.next();
                if (s.equals("send")) {
                    msg = scanner.nextLine();
                    try {
                        out.writeUTF(user+" "+msg);
                        out.flush();
                        System.out.println("\"" + msg + "\"" + " is sending");
                    } catch (IOException e) {
                        System.out.println("Err stream write data");
                        e.printStackTrace();
                    }

                }
                else if(s.equals("close")){
                    out.close();
                    running = false;
                    System.out.println("Closing Data stream");
                }
            }
            outputStream.close();
            socket.close();
            System.out.println("Socket close");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Err socket.getOutputStream");
        }
    }
}