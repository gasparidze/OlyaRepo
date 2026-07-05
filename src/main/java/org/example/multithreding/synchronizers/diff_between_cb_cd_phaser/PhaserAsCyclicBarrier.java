package org.example.multithreding.synchronizers.diff_between_cb_cd_phaser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

/**
 * Фазы здесь не проектировали, просто показали, каике мтеоды аналогичны поведению CyclicBarrier
 */
public class PhaserAsCyclicBarrier {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        Phaser phaser = new Phaser(3);
        threadPool.execute(new Task(phaser));
        threadPool.execute(new Task(phaser));
        threadPool.execute(new Task(phaser));
    }

    public static class Task implements Runnable {
        private Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            while (true) {
                phaser.arriveAndAwaitAdvance(); // работает так же как cyclicBarrier.await()
                // отправка сообщения
            }
        }
    }
}
