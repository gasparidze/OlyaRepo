package org.example.multithreding.thread_pool;

import java.util.concurrent.*;

public class ThreadPoolExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * Рассмотрим основное метода ThreadPool:
         * 1) submit() - отправляет задачи на выполнение
         * 2) shutDown() - закрывает пул как ресурс, ждет выполнения всех задач, которые мы отпраивли в пул
         * 3) shutDownNow() - закрывает пул как ресурс, завершает все потоки и возвращает те задачи, которые не успели выполниться
         *
         * Обычно используеют shutDown(), т.к. хотим дождаться завершения всех задач
         * Для того, чтобы указать время, которое мы можем ожидать, есть метод awaitTermination()
         *
         * 4) invokeAll(Collection) - можно отправлять сразу список задачи на выполнение
         */
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        threadPool.execute(() -> System.out.println("hello"));

        Future<Integer> future = threadPool.submit(() -> {
            Thread.sleep(2000L);
            System.out.println("It's callable");
            return 1;
        });

        System.out.println("Result: " + future.get());
        threadPool.shutdown();
        threadPool.awaitTermination(1L, TimeUnit.HOURS);

        System.out.println("main finishes");
    }
}
