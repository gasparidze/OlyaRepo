package org.example.multithreding.sync;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Создать класс Counter с одним полем: count
 * Добавить методы:
 * 1) getCount() - получаем поле
 * 2) increment() - увеличиваем count на единицу
 * 3) decrement() - уменьшаем count на единицу
 */
public class Counter {
    private int count;
//    private AtomicInteger counter = new AtomicInteger();
    private static String description;

    /**
     * Также можно синхронизировать статические методы
     * Здесь уже, т.к. метод статический и принадлежит классу, то и синхронзация будет происходить на мониторе класса
     */
    public static void init(){
        synchronized (Counter.class) {
            description = "Test Description";
        }
    }

    /**
     * counter++
     * counter = counter + 1
     * Операция инкремент не атомарна, т.е. она состоит из 3х подопераций:
     * 1) прочитать текущее значение counter
     * 2) прибавить к текущему значению единицу
     * 3) записать новое значение в counter
     *
     * В целях оптимизации каджый поток хранит значение в кеше ядра и когда поток запишет новое значение
     * в оператиую память - неизвестно, и также когда второй поток решить прочитать это значение с опертивки,
     * а не у себя из кеша, тоже неясно
     *
     * thread1 -> прочитали значение (3) -> прибавил единицу -> записал (4)
     * thread2 -> прочитали значение (3) -> прибавил единицу -> записал (4)
     * thread3 -> 3
     *
     * thread2 -> прочитали значение (3) -> прибавил единицу
     * thread3 -> прочитали значение (3) -> прибавил единицу -> записывает (4)
     *
     * synchronized - это замочек
     * первый поток зашел в increment поставил замок, и пока increment не выполнит все свои 3 подоперации и не запишет в оперативку, замок не снимется
     * и когда increment выполни все свои подоперации и записал в оператику новое значение, замок снимается и другие потоки могут вызывать метод increment
     */
    public void increment(){
        /**
         * Синхронизированный блок
         * и мы всегда синхронизируемся на мониторе какого-то объекта, в данном случае мы забираем монитор объекта Counter
         * У каждого объекта есть свой монитор. Монитор - это менанизм достижения синхронизации между потоками.
         *
         */
        synchronized (this) {
            count++;
        }
//        counter.incrementAndGet();
    }

    public synchronized void decrement(){
        count--;
//        counter.decrementAndGet();
    }

    public int getCount() {
        return count;
    }

    //    public AtomicInteger getCount() {
//        return counter;
//    }
}
