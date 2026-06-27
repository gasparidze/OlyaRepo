package org.example.multithreding.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecute {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
//        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);
        /**
         * С помощью execute() мы передали 10 раз задание RunnableImpl
         * после выполнения 10 заданий, threadPool не заканчивает работу, т.к. ждет новые задания,
         * поэтому мы должны заканчивать работу threadPool с помощью метода shutDown()
         */
        for (int i = 0; i < 10; i++) {
            threadPool.execute(new RunnableImpl());
//            threadPool1.execute(new RunnableImpl());
        }
        threadPool.shutdown();
        threadPool.awaitTermination(5 , TimeUnit.SECONDS);
        System.out.println("Main finishes");
    }
}

class RunnableImpl implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " begins work");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " ends work");
    }
}