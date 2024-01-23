package luke.learn.experiment;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(true);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);

                        int count = socketChannel.read(buffer);
                        while (count != -1) {
                            buffer.flip();
                            System.out.println(StandardCharsets.UTF_8.decode(buffer));
                            buffer.clear();
                            count = socketChannel.read(buffer);
                        }
                        socketChannel.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        Thread t = new Thread(r, "ServerThread");
        t.start();
        Thread.sleep(1000);
        System.out.println(t.getName()+":"+t.getState());
    }

}

