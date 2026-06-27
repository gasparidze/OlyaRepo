package org.example.multithreding.synchronizers.latch;

import java.util.concurrent.CountDownLatch;

public class BlackFridayEx {
    private static CountDownLatch countDownLatch = new CountDownLatch(3);

    private static void marketStaffIsOnPlace() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("Market staff came to work");
        countDownLatch.countDown();
        System.out.println("countDownLatch: " + countDownLatch.getCount());
    }

    private static void everythingIsReady() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("Everything is ready, so let's open market");
        countDownLatch.countDown();
        System.out.println("countDownLatch: " + countDownLatch.getCount());
    }

    private static void openMarket() throws InterruptedException {
        Thread.sleep(4000);
        System.out.println("Market is opened");
        countDownLatch.countDown();
        System.out.println("countDownLatch: " + countDownLatch.getCount());
    }

    public static void main(String[] args) throws InterruptedException {
        new Friend("Test1", countDownLatch);
        new Friend("Test2", countDownLatch);
        new Friend("Test3", countDownLatch);
        new Friend("Test4", countDownLatch);
        new Friend("Test5", countDownLatch);

        /**
         * Каждое из этих действий уменьшит счетчик на единицу
         */
        marketStaffIsOnPlace();
        everythingIsReady();
        openMarket();
    }
}
