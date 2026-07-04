package org.example.multithreding.synchronizers.diff_between_cb_cd_phaser;

import java.util.concurrent.*;

/**
 * Предположим, у нас игра, в которой 3 игрока и мы хотим повторно отправлять специальное сообщение всем игрокам,
 * но мы должны убедиться, что сообщение придет всем игрокам одновременно, чтоб это реализовать используем CyclicBarrier
 */
public class CyclicBarrierEx {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(4);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        threadPool.submit(new Task(cyclicBarrier));
        threadPool.submit(new Task(cyclicBarrier));
        threadPool.submit(new Task(cyclicBarrier));
    }

    public static class Task implements Runnable {
        private CyclicBarrier cyclicBarrier;

        public Task(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        /**
         * Задачи запускаются в бесконечном цикле, потому что хотим показать, что барьер может работать бесконечно,
         * в отличие от CountDownLatch
         * как только потоки разблокируются и сообщение отправится, бесконечный цикл заставит потоки снова добираться до барьера,
         * тем самым показывая главное различие между CountDownLatch, что барьер можно переиспользовать бесконечно количество раз
         */
        @Override
        public void run() {
            while (true) {
                /**
                 * все 3 потока могут добраться до await() в разное время, но все они будут заблокированы
                 * пока все потоки не дойдут до барьера
                 * как только все 3 потока доберутся, барьер сломается, все 3 потока разблокируются и одновременно отправят сообщение
                 */
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }
            // отправка сообщения
        }
    }
}
