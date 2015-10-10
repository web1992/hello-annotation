package com.web.tcp;

import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import static java.lang.System.out;

/**
 * Created by web on 2015/10/10.
 */
public class MyClient {

    private final static String IP="127.0.0.1";

    private final static int PORT=8084;

    public static void main(String[] args) throws  Exception{

        Socket socket=new Socket(IP,PORT);
        InetAddress address=socket.getInetAddress();
        out.println(address.getHostAddress());
        out.println(address.getHostName());

        OutputStream out= socket.getOutputStream();
        String msg= "hello world .";
        byte[] _b=msg.getBytes();
        out.write(_b);
        socket.close();

    }
}


