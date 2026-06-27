package org.example.multithreding.synchronizers.semaphore.callbox;

import java.util.concurrent.Semaphore;

/**
 * 2 телефонные бутки и 5 желающих позвонить
 */
public class CallBox {
    public static void main(String[] args) {
        Semaphore callbox = new Semaphore(2);
        new Person("Test1", callbox);
        new Person("Test2", callbox);
        new Person("Test3", callbox);
        new Person("Test4", callbox);
        new Person("Test5", callbox);
    }
}
