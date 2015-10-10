package com.web.tcp;

import java.io.*;
import java.net.*;

public class ServerSystem {
    ServerSocket server = null;
    Socket clientsocket = null;
    int numOfConnections = 0, port;

    public ServerSystem(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        int port = 1234;
        ServerSystem ss = new ServerSystem(port);
        ss.startServer();
    }

    public void startServer() {
        try {
            server = new ServerSocket(port);
        } catch (Exception e) {
            System.out.println("Error occured." + e);
        }

        System.out.println("Server has started. Ready to accept connections.");

        while (true) {
            try {
                clientsocket = server.accept();
                numOfConnections++;
                ServerConnection sc = new ServerConnection(clientsocket, numOfConnections, this);
                new Thread(sc).start();
            } catch (Exception e) {
                System.out.println("Error occured." + e);
            }
        }
    }

    public void stopServer() {
        System.out.println("Terminating connection");
        System.exit(0);
    }
}

class ServerConnection extends Thread {
    BufferedReader br;
    PrintStream ps;
    Socket clientsocket;
    int id;
    ServerSystem ss;

    public ServerConnection(Socket clientsocket, int numOfConnections, ServerSystem ss) {
        this.clientsocket = clientsocket;
        id = numOfConnections;
        this.ss = ss;

        System.out.println("Connection " + id + " established with " + clientsocket);
        try {
            br = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
            ps = new PrintStream(clientsocket.getOutputStream());
        } catch (Exception e) {
            System.out.println("Error occured." + e);
        }
    }

    public void run() {
        String line;
        try {
            boolean stopserver = false;
            while (true) {
                line = br.readLine();
                System.out.println("Received string: " + line + " from connection " + id);
                long threadID = Thread.currentThread().getId();
                System.out.println("Thread ID: " + threadID + " is doing the current task.");

                if (line.equals("exit")) {
                    stopserver = true;
                    break;
                } else {
                    int len = line.length();
                    String reversedstring = "";
                    for (int i = len - 1; i >= 0; i--)
                        reversedstring = reversedstring + line.charAt(i);
                    ps.println("" + reversedstring);

                }
            }
            System.out.println("Connection " + id + " is closed.");
            br.close();
            ps.close();
            clientsocket.close();

            if (stopserver)
                ss.stopServer();
        } catch (Exception e) {
            System.out.println("Error occured." + e);
        }
    }
}