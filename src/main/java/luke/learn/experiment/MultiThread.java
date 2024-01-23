package luke.learn.experiment;

import java.util.concurrent.BrokenBarrierException;

public class MultiThread extends Thread {

    private int count;

    public MultiThread(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println(getName() + ":打了" + i + "个小兵");

            try {
                sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

        MultiThread t1=new  MultiThread(100);
        MultiThread t2=new  MultiThread(100);
        MultiThread t3=new  MultiThread(50);
//设置线程的名字
        t1.setName("鲁班");
        t2.setName("刘备");
        t3.setName("亚瑟");

//        t1.setDaemon(true);
        t2.setDaemon(true);
//启动线程
        t1.start();
        t2.start();
       t3.start();

        System.out.println("This is the end~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

//    private static volatile int counter = 0;
//
//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    for (int i = 0; i < 10000; i++)
//                        counter++;
//                }
//            });
//            thread.start();
//        }
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(counter);
//    }
}


