package luke.learn.netty;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class WorkerThread extends Thread {

    private SocketChannel socketChannel;

    public WorkerThread(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = socketChannel.read(buffer);

            if (count != -1) {
                buffer.flip();
                System.out.println(StandardCharsets.UTF_8.decode(buffer));
                buffer.clear();
            } else {
                socketChannel.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
