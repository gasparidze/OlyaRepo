package org.example.multithreding;

public class ThreadExample extends Thread {
    @Override
    public void run() {
        System.out.println("Hello from thread: " + getName());
    }
}
