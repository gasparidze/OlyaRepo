package org.example.multithreding.synchronizers.semaphore.queue;

import java.util.concurrent.Semaphore;

public class CashboxTest {
    public static void main(String[] args) {
        /**
         * в данном примере как такогового ресурса нет, к которому обращаемся
         * semaphore - это просто охранник, который пускает только 2 потока одновременно
         */
        Semaphore semaphore = new Semaphore(2);

        new Thread(new BuyerThread(semaphore)).start();
        new Thread(new BuyerThread(semaphore)).start();
        new Thread(new BuyerThread(semaphore)).start();
        new Thread(new BuyerThread(semaphore)).start();
        new Thread(new BuyerThread(semaphore)).start();
        new Thread(new BuyerThread(semaphore)).start();
        new Thread(new BuyerThread(semaphore)).start();
        new Thread(new BuyerThread(semaphore)).start();
    }
}
