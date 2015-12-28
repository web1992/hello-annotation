package com.web.io.nio.buffer;


import  static  java.lang.System.out;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by erbao.wang on 2015/12/28.
 *
 * @desc
 */
public class Main {

    private volatile boolean isLive = true;
    private Selector selector;
    public static final int PORT = 8000;

    public static void main(String[] args) throws Exception {

        Main main = new Main();
        main.initServer();
        main.start();


    }

    /**
     * 启动服务
     */
    public void start() throws IOException {

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                try {
                    out.println("server thread run.");
                    msgReceive();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        ExecutorService executorService= Executors.newFixedThreadPool(1);
        executorService.submit(runnable);
        out.println("server start.");
    }

    private void msgReceive() throws IOException {

        Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
        out.println("msgReceive run.");
        while (isLive){
            while (iterator.hasNext()) {
                SelectionKey _key = iterator.next();
                // 删除注册的Selection
                iterator.remove();
                if (_key.isAcceptable()) {
                    // 有可接收的消息
                    // 写数据给客户端
                    write(_key);

                } else if(_key.isReadable()){
                    // 有可读的数据
                    read(_key);
                }else{
                    //
                    out.println("other run.");
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


    private void write(SelectionKey _key) throws IOException {
        ServerSocketChannel socketChannel = (ServerSocketChannel) _key.channel();

        SocketChannel channel = socketChannel.accept();
        ByteBuffer _buff = ByteBuffer.allocate(100);
        channel.read(_buff);
        channel.write(ByteBuffer.wrap(new String("server send msg.").getBytes()));

        channel.register(this.selector, SelectionKey.OP_READ);
    }


    /**
     * 初始化服务器
     *
     * @throws IOException
     */
    public void initServer() throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        SocketAddress address = new InetSocketAddress(PORT);
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(address);

        selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    }

    /**
     * 关闭服务器
     */
    public void stop() {
        isLive = false;
    }

}
