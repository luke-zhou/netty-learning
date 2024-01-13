package luke.learn.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8080));
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("Luke, testing block server".getBytes(StandardCharsets.UTF_8));
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();
        socketChannel.close();
    }
}
