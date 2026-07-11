package org.example.multithreding.sync_collections.concurrent_collections;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueExample {
    public static void main(String[] args) {
        /**
         * put() - добавляем элемент в конец очереди
         * take() - берем элемент из начала очереди
         *
         * Если очередь заполнена, put() будет ждать пока не освободится место
         * Если Producer работает медленее, чем Consumer, т.е. элементов в очереди не будет,
         * то тогда метод take() будет ждать появления в очереди нового элемента
         */
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(4);

        /**
         * Producer
         */
        new Thread(() -> {
            int i = 0;
            while (true) {
                try {
                    queue.put(++i);
                    System.out.println("Producer добавил новый элемент: " + i + " " + queue);
                    Thread.sleep(3000 );
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        /**
         * Consumer
         */
        new Thread(() -> {
            while (true) {
                try {
                    Integer element = queue.take();
                    System.out.println("Consumer взял один элемент: " + element + " " + queue);
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}