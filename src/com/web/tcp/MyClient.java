package com.web.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;


/**
 * Created by web on 2015/10/10.
 */
public class MyClient {

    private final static String IP="127.0.0.1";

    private final static int PORT=8084;

    public static void main(String[] args) throws  Exception{

        Socket socket=new Socket(IP,PORT);
        InetAddress address=socket.getInetAddress();
        System.out.println(address.getHostAddress());
        System.out.println(address.getHostName());

        OutputStream _out= socket.getOutputStream();
        InputStream _in= socket.getInputStream();
        String msg= "hello world .";
        byte[] _b=msg.getBytes();
        _out.write(_b);
        _out.close();
        _in.close();
        socket.close();
        System.out.println("client end.");

    }
}


