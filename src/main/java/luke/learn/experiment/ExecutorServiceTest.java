package luke.learn.experiment;

import java.util.concurrent.*;

public class ExecutorServiceTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Callable<String> task = () -> "Hello from " + Thread.currentThread().getName();

        Future[] futures = new Future[10];
        for(int i =0; i<10; i++){
            futures[i] = executorService.submit(task);
        }

        for(int i =0; i<10; i++){
            System.out.println(futures[i].get());
        }

        executorService.shutdown();
    }
}
