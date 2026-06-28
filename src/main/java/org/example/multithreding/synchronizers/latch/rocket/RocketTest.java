package org.example.multithreding.synchronizers.latch.rocket;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RocketTest {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(RocketDetail.values().length);

        /**
         * Пул из 3х потоков будет по очереди обрабатывать наши детали, деталей у нас пять, а потоков - 3
         */
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        /**
         * Сначала пытаемся запустить нашу ракет, она будет ждать
         */
        threadPool.execute(new Rocket(countDownLatch));

        /**
         *  т.к. наш threadPool на 3 потока, то после потока rocket, у нас осталось 2 свободных потока для конструирования
         *  наших деталей => в консоле будем видеть по 2 детали вместе
         */
        Arrays.stream(RocketDetail.values())
                        .map(rocketDetail -> new RocketDetailRunnable(rocketDetail, countDownLatch))
                        .forEach(threadPool::execute);

        threadPool.shutdown();
    }
}
