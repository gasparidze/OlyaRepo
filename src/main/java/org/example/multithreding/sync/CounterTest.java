package org.example.multithreding.sync;

/**
 * В методе main создадим 10 потока типа CounterThread, передав один и тот же объект Counter и запустим все потоки,
 * дождемся их выполнения и выведем на консоль текущее значение counter (счетчика)
 */
public class CounterTest {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        CounterThread counterThread1 = new CounterThread(counter);
        CounterThread counterThread2 = new CounterThread(counter);
        CounterThread counterThread3 = new CounterThread(counter);
        CounterThread counterThread4 = new CounterThread(counter);
        CounterThread counterThread5 = new CounterThread(counter);
        CounterThread counterThread6 = new CounterThread(counter);
        CounterThread counterThread7 = new CounterThread(counter);
        CounterThread counterThread8 = new CounterThread(counter);
        CounterThread counterThread9 = new CounterThread(counter);
        CounterThread counterThread10 = new CounterThread(counter);

        counterThread1.start();
        counterThread2.start();
        counterThread3.start();
        counterThread4.start();
        counterThread5.start();
        counterThread6.start();
        counterThread7.start();
        counterThread8.start();
        counterThread9.start();
        counterThread10.start();

        counterThread1.join();
        counterThread2.join();
        counterThread3.join();
        counterThread4.join();
        counterThread5.join();
        counterThread6.join();
        counterThread7.join();
        counterThread8.join();
        counterThread9.join();
        counterThread10.join();

        System.out.println(counter.getCount());
    }
}
