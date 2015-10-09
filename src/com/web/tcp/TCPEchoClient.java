package com.web.tcp;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class TCPEchoClient {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // �жϴӿ���̨���ܵĲ����Ƿ���ȷ
        if ((args.length < 2) || (args.length > 3))
            throw new IllegalArgumentException(
                    "Parameter(s):<Server><Word>[<Port>]]");
        // ��ȡ��������ַ
        String server = args[0];
        // ��ȡ��Ҫ���͵���Ϣ
        byte[] data = args[1].getBytes();
        // ����������Ӳ�����ô�ͻ�ȡ������Ϣ�Ķ˿ںţ�Ĭ�϶˿ں�Ϊ8099
        int servPort = (args.length == 3) ? Integer.parseInt(args[2]) : 8099;
        // ���ݷ�������ַ�Ͷ˿ں�ʵ����һ��Socketʵ��
        Socket socket = new Socket(server, servPort);
        System.out.println("Connected to server...sending echo string");
        // ���ش��׽��ֵ������������ӷ��������ܵ����ݶ���
        InputStream in = socket.getInputStream();
        // ���ش��׽��ֵ��������������������͵����ݶ���
        OutputStream out = socket.getOutputStream();
        // ����������ʹӿ���̨���յ�����
        out.write(data);
        // �������ݵļ���������д�����ݵĳ�ʼƫ����
        int totalBytesRcvd = 0;
        // ��ʼ���������ݵ����ֽ���
        int bytesRcvd;
        while (totalBytesRcvd < data.length) {
            // �������ر����ӣ��򷵻� -1,read�������ؽ������ݵ����ֽ���
            if ((bytesRcvd = in.read(data, totalBytesRcvd, data.length
                    - totalBytesRcvd)) == -1)
                throw new SocketException("the connnection with server had close.");
            totalBytesRcvd += bytesRcvd;
        }
        // ��ӡ������������������
        System.out.println("Received:" + new String(data));
        // �ر�����
        socket.close();

    }

}