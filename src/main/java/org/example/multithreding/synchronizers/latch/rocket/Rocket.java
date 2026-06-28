package org.example.multithreding.synchronizers.latch.rocket;

import java.util.concurrent.CountDownLatch;

/**
 * У нас будет ракета, которая состоит из деталей. Как только все детали юудут собраны,
 * ракета полетит, до этого она бдует ждать своего пуска
 */
public class Rocket implements Runnable {
    private final CountDownLatch countDownLatch;

    public Rocket(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("Ракета готовится к запуску...");
        try {
            countDownLatch.await();
            // как только await() отпускает наш поток, мы делаем Пуск
            System.out.println("Пуск!!!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
