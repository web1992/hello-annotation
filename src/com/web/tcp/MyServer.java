package com.web.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
            InputStream _in= socketConn.getInputStream();
            OutputStream _out=socketConn.getOutputStream();
            byte[] _readData=new byte[BUF_SIZE];
            int _len=0;
            while ( true ){
                _len=_in.read(_readData);
                if(_len == -1){
                    break;
                }
                System.out.println(new String(_readData));

            }
            // �ͻ��˹ر�����ʱ���ر�����
            // �ͻ������е����ݶ���ȡ���ˣ����Ӿ��Զ��ر���
            System.out.println(" client close connection.");
            _in.close();
            _out.close();
            socketConn.close();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("server start >>...");
        new MyServer().start();
        System.out.println("server shutdown >>...");
    }
}