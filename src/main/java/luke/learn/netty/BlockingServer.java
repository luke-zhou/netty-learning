package luke.learn.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class BlockingServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(true);
        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            int count = socketChannel.read(buffer);
            while (count!=-1) {
                buffer.flip();
                System.out.println(StandardCharsets.UTF_8.decode(buffer));
                buffer.clear();
                count = socketChannel.read(buffer);
            }
            socketChannel.close();
        }
    }
}
