package org.example.multithreding.synchronizers.semaphore.callbox;

import java.util.concurrent.Semaphore;

public class Person extends Thread{
    private String name;
    private Semaphore callbox;

    public Person(String name, Semaphore callbox) {
        this.name = name;
        this.callbox = callbox;
        start();
    }

    @Override
    public void run() {
        System.out.println(name + " ждет...");
        try {
            callbox.acquire();
            System.out.println(name + " пользуется телефоном");
            Thread.sleep(2000);
            System.out.println(name + " завершил звонок");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            callbox.release();
        }
    }
}
