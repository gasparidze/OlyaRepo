package org.example.multithreding.sync_collections.concurrent_collections;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListExample {
    public static void main(String[] args) throws InterruptedException {
        /**
         * Также выбросится ConcurrentModificationException
         * Чтобы исправить эту ситуацию, нужно просто использовать CopyOnWriteArrayList
         */
//        List<String> list = new ArrayList<>();
        List<String> list = new CopyOnWriteArrayList<>();
        list.add("Kate");
        list.add("Dasha");
        list.add("Alina");
        list.add("Arina");
        list.add("Maria");
        System.out.println(list);

        /**
         * Перед тем, как мы начали итерироваться по всей коллекции, состояние коллекции - исходное,
         * что происходит в параллельных потоках нашему потоку неважно
         *
         * При использовании CopyOnWriteArrayList во время чтения элементов из коллекции вообще никакие блокировки не ставятся
         */
        Runnable runnable1 = () -> {
            for (String s : list) {
                try {
                    Thread.sleep(100);
                    System.out.println(s);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        /**
         * В данном потоке создалось несколько копий нашей коллекции: при вставке и при удалении
         */
        Runnable runnable2 = () -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list.remove(4);
            list.add("Elena");
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        /**
         * Только после того, как мы закончили работу, старые копии уже не нужны
         * и list здесь работает с самой новой копией
         *
         * Таким образом, процесс создания копий - затратный
         * Если CopyOnWriteArrayList содержит много элементов, то для создания копий понадобится какое-то время
         */
        System.out.println(list);
    }
}
