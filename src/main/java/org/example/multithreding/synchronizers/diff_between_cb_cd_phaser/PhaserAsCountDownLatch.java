package org.example.multithreding.synchronizers.diff_between_cb_cd_phaser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

/**
 * Фазы здесь не проектировали, просто показали, каике мтеоды аналогичны поведению CountDownLatch
 */
public class PhaserAsCountDownLatch {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        /**
         *  Создаем 3 участника. НО это НЕ потоки!
         *  просто 3 "слота" для прибытия
         */
        Phaser phaser = new Phaser(3);

        threadPool.execute(new DependentService(phaser));
        threadPool.execute(new DependentService(phaser));
        threadPool.execute(new DependentService(phaser));

        /**
         * Если текущая фаза равно переданному числу phase, то awaitAdvance ждет, пока остальные потоки не вызовут arrive()
         * Если текущая фаза НЕ равна переданному числу phase, то awaitAdvance не ждет и сразу возвращает текущую фазу
         */
        phaser.awaitAdvance(0);
        System.out.println("Все зависимые сервисы проинициализированы");
    }

    public static class DependentService implements Runnable {
        private Phaser phaser;

        public DependentService(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " выполняется...");
            phaser.arrive();
            // не останавливается и дальше продолжает выполнять команды
        }
    }
}
