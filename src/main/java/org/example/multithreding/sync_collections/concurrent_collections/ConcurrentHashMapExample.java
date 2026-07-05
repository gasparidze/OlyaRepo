package org.example.multithreding.sync_collections.concurrent_collections;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {
    public static void main(String[] args) throws InterruptedException {
        /**
         * С HashMap такой код не пройдет, т.к. итератор такое пропустит и выбросит ConcurrentModificationException
         * Чтобы исправить ситуацию, нужно просто использовать ConcurrentHashMap
         */
//        Map<Integer, String> map = new HashMap<>();
        Map<Integer, String> map = new ConcurrentHashMap<>();
        map.put(1, "Kate");
        map.put(2, "Dasha");
        map.put(3, "Alina");
        map.put(4, "Arina");
        map.put(5, "Maria");
        System.out.println(map);

        /**
         * При использовании ConcurrentHashMap вообще никакие блокировки не ставятся
         */
        Runnable runnable1 = () -> {
            for (Integer key : map.keySet()) {
                try {
                    Thread.sleep(100);
                    System.out.println(key + ":" + map.get(key));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable runnable2 = () -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            /**
             * при использовании ConcurrentHashMap, во время добавления, будет заблокирован только 1 сегмент нашей мапы
             */
            map.put(6, "Elena");
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(map);
    }
}
