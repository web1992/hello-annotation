package com.web.tcp;

import java.net.*;
import java.text.MessageFormat;
import java.io.*;

public class TCPEchoServer {

    private static final int BUFSIZE = 32;

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // �ӿ���̨��ȡ��Ҫ�����Ķ˿ں�
        if (args.length != 1)
            throw new IllegalArgumentException("Parameter(s):<Port>");
        // ��ȡ�˿ں�
        int servPort = Integer.parseInt(args[0]);
        // ʵ����һ��ServerSocket����ʵ��
        ServerSocket servSocket = new ServerSocket(servPort);
        System.out.println(MessageFormat.format("starting port:{0}", args[0]));

        // ��ʼ�������ݵ����ֽ���
        int recvMsgSize;
        // �������ݵĻ�����
        byte[] receiveBuf = new byte[BUFSIZE];

        // ѭ�������������˿ں�,�����µ���������
        while (true) {
            // �����ȴ���ÿ���յ�һ������ʹ���һ���µ�����ʵ��
            Socket clntSocket = servSocket.accept();
            // ��ȡ���ӵĿͻ��˵� SocketAddress
            SocketAddress clientAddress = clntSocket.getRemoteSocketAddress();
            // ��ӡ������ӿͻ��˵�ַ��Ϣ
            System.out.println("Handling client at" + clientAddress);
            // �ӿͻ��˽������ݵĶ���
            InputStream in = clntSocket.getInputStream();
            // ��ͻ��˷������ݵĶ���
            OutputStream out = clntSocket.getOutputStream();
            // ��ȡ�ͻ��˷��͵����ݺ��ٷ��͵��ͻ���
            while ((recvMsgSize = in.read(receiveBuf)) != -1) {
                out.write(receiveBuf, 0, recvMsgSize);
            }
            // �ͻ��˹ر�����ʱ���ر�����
            System.out.println(" client close connection.");
            clntSocket.close();
        }

    }

}