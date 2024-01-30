package luke.learn.netty;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NonBlockingServer {
    public static void main(String[] args) throws IOException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        ExecutorService masterGroup = Executors.newFixedThreadPool(1);
        ExecutorService workerGroup = Executors.newFixedThreadPool(5);
        bootstrap.group(masterGroup, workerGroup).bind(8080);
    }
}
