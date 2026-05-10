package org.example.multithreding;

public class MultiExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        ThreadExample threadExample = new ThreadExample();
        threadExample.start();

        RunnableExample runnableExample = new RunnableExample();
        Thread runnableThread = new Thread(runnableExample);
        runnableThread.start();

        Thread lambdaJoin = new Thread(() -> System.out.println("hello from lambda thread: " + Thread.currentThread().getName()));
        lambdaJoin.start();

        threadExample.join();
        runnableThread.join();
        lambdaJoin.join();

        System.out.println("123");
        System.out.println("123");
        System.out.println("123");
        System.out.println("123");
        System.out.println("123");
        System.out.println("123");
        System.out.println("123");
        System.out.println("123");
        System.out.println("123");
        System.out.println("123");
        System.out.println("123");
    }
}