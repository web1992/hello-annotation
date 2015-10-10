package com.web.tcp;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.out;

class MyServer {

    private final static String IP = "127.0.0.1";

    private final static int PORT = 8084;
    private final static int BUF_SIZE = 1024*1;

    public MyServer() {
    }

    public void start() throws Exception {
        ServerSocket myServer = new ServerSocket(PORT);

        while (true){
            Socket socketConn= myServer.accept();
            InputStream in= socketConn.getInputStream();
            byte[] _readData=new byte[BUF_SIZE];
            int _len=0;
            while ( (_len=in.read(_readData)) !=-1 ){
                out.println(new String(_readData));
            }
            // 客户端关闭连接时，关闭连接
            // 客户端所有的数据都读取完了，链接就自动关闭了
            System.out.println(" client close connection.");
            socketConn.close();
        }
    }

    public static void main(String[] args) throws Exception {
        out.println("server start >>...");
        new MyServer().start();
        out.println("server shutdown >>...");
    }
}