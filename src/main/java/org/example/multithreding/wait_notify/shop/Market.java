package org.example.multithreding.wait_notify.shop;

/**
 * У нас будет завод по производству хлеба. Будет класс Producer, который будет производить хлеб (но не более 5 штук) и
 * класс Consumer, который будет потреблять/покупать этот хлеб
 *
 * Как работает: notify у методов может отрабатывать в холостую, т.к. никакой поток не находится в состоянии ожидания,
 * а notify срабатывает
 * Может быть и так, что, например, поток consumer находится в состоянии ожидания, у потока producer срабатывает notify,
 * но далее монитор может захватить все также producer, а не consumer, так пргоисходит потому что оба потока активны
 * и оба борятся за монитор, кто заберет - это уже непредугадать, тут как решит процессор
 *
 * Важно: сам метод notify не освобождает монитор, монитор освобождается именно после завершения метода
 * При каждом запуске может быть разный output, т.к. потоки постоянно борятся за монитор
 */
public class Market {
    private int breadCount = 0;

    public synchronized void getBread() {
        while (breadCount < 1){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        breadCount--;
        System.out.println("Потребитель купил 1 хлеб");
        System.out.println("Количество хлеба в магазине: " + breadCount);
        notify();
    }

    public synchronized void putBread() {
        while (breadCount >= 5){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        breadCount++;
        System.out.println("Производитель добавил на витрину 1 хлеб");
        System.out.println("Количество хлеба в магазине: " + breadCount);
        notify();
    }
}

class Producer implements Runnable {
    private final Market market;

    public Producer(Market market) {
        this.market = market;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            market.putBread();
        }
    }
}

class Consumer implements Runnable {
    private final Market market;

    public Consumer(Market market) {
        this.market = market;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            market.getBread();
        }
    }
}