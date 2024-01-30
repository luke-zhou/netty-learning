package luke.learn.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerBootstrap {
    private ExecutorService masterGroup;
    private ExecutorService workerGroup;
    private int port;

    public ServerBootstrap group(ExecutorService masterGroup, ExecutorService workerGroup) {
        this.masterGroup = masterGroup;
        this.workerGroup = workerGroup;
        return this;
    }

    public void bind(int port) throws IOException {
        this.port = port;
        do_bind(this.port, this.workerGroup);
    }

    private void do_bind(int port, ExecutorService workerGroup) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);

        Thread masterThread = new MasterThread(serverSocketChannel, workerGroup);

        masterGroup.submit(masterThread);
    }
}
