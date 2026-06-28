package org.example.multithreding.synchronizers.cyclic_barrier.rocket;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Отличие от CountDownLatch в том, что он использует countDown() и await(), все потоки который вызвали await() ждут
 * до тех пор пока определенное количество раз (задается в конструкторе при создании CountDownLatch) другие потоки не вызвали метод countDown()
 * CyclicBarrier же не имеет метод countDown(), имеет только await(), все потоки, которые вызывают await() ждут
 * до тех пор пока определенное количество потоков (задается в конструкторе при создании CyclicBarrier) не вызовет await()
 * и как только это количество совпадает все потоки продолжат свое выполнение
 */
public class RocketTest {
    public static void main(String[] args) {
        /**
         * данный runnable будет выполняться сразу после того, как await отпустит потоки, но до того, как потки пойдут выполняться дальше
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(RocketDetail.values().length, () -> System.out.println("Пуск!!!"));

        /**
         * Пул из 3х потоков будет по очереди обрабатывать наши детали, деталей у нас пять, а потоков - 3
         */
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        ExecutorService threadPool = Executors.newCachedThreadPool();

        /**
         *  т.к. наш threadPool на 3 потока, то после потока rocket, у нас осталось 2 свободных потока для конструирования
         *  наших деталей => в консоле будем видеть по 2 детали вместе
         */
        Arrays.stream(RocketDetail.values())
                        .map(rocketDetail -> new RocketDetailRunnable(rocketDetail, cyclicBarrier))
                        .forEach(threadPool::execute);

        threadPool.shutdown();
    }
}
