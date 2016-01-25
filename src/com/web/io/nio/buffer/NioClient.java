package com.web.io.nio.buffer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.out;

/**
 * Created by erniu on 2015/12/28.
 * <p/>
 * 基于SocketChannel 的客户端的基本实现
 */
public class NioClient {
    public static final int PORT = 1234;
    private Selector selector;
    private String IP = "127.0.0.1";

    public static void main(String[] args) throws Exception {

        NioClient client = new NioClient();
        client.initClient();
        client.start();
        while (true){
            System.out.println("please enter:");
            Scanner in =new Scanner(System.in);
            String input=in.nextLine();
            client.writeMsg(input);
        }

    }

    private  void writeMsg(String msg) throws IOException {
            selector.select();
            Iterator iter = selector.selectedKeys().iterator();
            while (iter.hasNext()) {
                SelectionKey _key = (SelectionKey) iter.next();
                iter.remove();
                //
                if (_key.isConnectable()) {
                    SocketChannel channel = (SocketChannel) _key.channel();
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                    }
                    channel.configureBlocking(false);


                    channel.write(ByteBuffer.wrap(new String(msg).getBytes()));
                    channel.register(selector, SelectionKey.OP_WRITE);
                }

                if(_key.isWritable()){
                    SocketChannel channel = (SocketChannel) _key.channel();
                    channel.write(ByteBuffer.wrap(new String(msg).getBytes()));
                    channel.register(selector, SelectionKey.OP_WRITE);
                }
            }

    }

    /**
     * 启动
     */
    private void start() throws IOException {
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                this.run();
            }
        };

        ExecutorService executorService= Executors.newFixedThreadPool(1);
        executorService.submit(runnable);


    }

    private void run() throws IOException {
        while (true) {
            Iterator iter = selector.selectedKeys().iterator();
            while (iter.hasNext()) {
                SelectionKey _key = (SelectionKey) iter.next();
                iter.remove();
                if (_key.isConnectable()) {
                    write(_key);
                }
                if (_key.isReadable()) {
                    read(_key);
                }
            }
        }
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer=ByteBuffer.allocate(100);
        channel.read(buffer);
        byte[] msg=buffer.array();
        out.println("receive msg "+new String(msg));
    }

    private void write(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        if (channel.isConnectionPending()) {
            channel.finishConnect();
        }
        channel.configureBlocking(false);

        channel.write(ByteBuffer.wrap(new String("client say hello I`m client.").getBytes()));
        channel.register(selector, SelectionKey.OP_READ);
    }

    /**
     * 初始化客户端
     *
     * @throws IOException
     */
    private void initClient() throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        this.selector = Selector.open();
        channel.connect(new InetSocketAddress(IP, PORT));
        // 第一次进行连接事件的注册，
        // 相当于建立连接
        channel.register(selector, SelectionKey.OP_CONNECT);
    }

}
