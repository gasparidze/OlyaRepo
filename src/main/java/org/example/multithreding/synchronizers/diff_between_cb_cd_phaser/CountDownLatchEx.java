package org.example.multithreding.synchronizers.diff_between_cb_cd_phaser;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchEx {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(4);
        CountDownLatch countDownLatch = new CountDownLatch(3);

        /**
         * все 3 потока могут выполнить countDown() в разный промежуток времени
         */
        threadPool.submit(new DependentService(countDownLatch));
        threadPool.submit(new DependentService(countDownLatch));
        threadPool.submit(new DependentService(countDownLatch));

        countDownLatch.await();
        System.out.println("Все зависимые сервисы проинициализирован");
    }

    public static class DependentService implements Runnable {
        private CountDownLatch countDownLatch;

        public DependentService(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(500);
                /**
                 * начало выполнения задания, здесь могла бы быть, например, логика подключения к БД,
                 * когда мы подключены, даем знать об этом с помощью countDown()
                 * и продолжаем далее работу метода run()
                 */
                System.out.println(Thread.currentThread().getName() + " проинициализирован");
                countDownLatch.countDown();
                // продолжение выполнения остальных операций задачи
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
