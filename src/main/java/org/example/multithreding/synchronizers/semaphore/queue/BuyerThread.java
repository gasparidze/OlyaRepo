package org.example.multithreding.synchronizers.semaphore.queue;

import java.util.concurrent.Semaphore;

/**
 * Класс покупателя, наших покупателей будут обсулживать 2 кассы
 */
public class BuyerThread implements Runnable{
    // очередь касс
    private final Semaphore semaphore;

    public BuyerThread(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            /**
             * Как только захватим монитор при вызове acquire, сразу можем выполнять код, который идет далее
             * если же не получилось захватить монитор, то поток просто ждет на этой строчке кода
             *
             * метод acquire предназначен для попытки разрешения семафора
             * acquire() заблокирует поток пока ресус не будет доступен для нас,
             * после доступности мы получаем разрешение использовать общий ресурс и counter семафора уменьшится на единицу
             */
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " обслуживается в кассе");
            Thread.sleep(5L);
            System.out.println(Thread.currentThread().getName() + " освобождаем кассу");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            /**
             * Мы обчзательно должны вызвать на семафоре release(), который говорит о том, что
             * мы освобождаем ращрегение семафора, тем самым counter (счетчик) увеличвается на единицу
             */
            semaphore.release();
        }
    }
}
