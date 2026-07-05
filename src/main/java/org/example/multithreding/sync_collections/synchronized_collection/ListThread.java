package org.example.multithreding.sync_collections.synchronized_collection;

import java.util.List;

public class ListThread extends Thread {
    /**
     * Будем из разных потоков обращаться к коллекции
     */
    private final List<Integer> list;

    public ListThread(List<Integer> list) {
        this.list = list;
        start();
    }

    /**
     * Есть несколько вариантов решения данной проблемы:
     * 1) использовать sync блок в методе run(), для этого надо захватить монитор объекта, который мы изменяем в потоках,
     * т.е. монитор list
     * Важно: но это не решает проблему, т.к. любой другой поток может вызвать другие методы list, потому что они не синхронизированы,
     * синхронизирован только данный цикл
     *
     */
    @Override
    public void run() {
        for (int i = 0; i < 400; i++) {
//            synchronized (list) {
                list.add(i);
//            }
        }
    }
}
