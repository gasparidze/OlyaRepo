package org.example.multithreding;

public class RunnableExample implements Runnable{
    @Override
    public void run() {
        System.out.println("Hello from runnable thread: " + Thread.currentThread().getName());
    }
}
