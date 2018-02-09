package com.samle.tools;

import java.io.*;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

public class Tcpclient {
    public static final String PROPERTIES = "client.properties";
    InputStream inputStream;
    private Socket socket;
    private String msg;
    private String user;
    Properties prop = new Properties();
    public void init() {
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES);
            prop.load(inputStream);

            //Scanner scanner = new Scanner(System.in);
            String host = prop.getProperty("host");
            int port = Integer.parseInt(prop.getProperty("port"));
            System.out.println("Connecting Host "+host);
            System.out.println("Port "+port);
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