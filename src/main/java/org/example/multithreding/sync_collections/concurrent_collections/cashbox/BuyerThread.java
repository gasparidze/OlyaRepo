package org.example.multithreding.sync_collections.concurrent_collections.cashbox;

import java.util.concurrent.BlockingQueue;

/**
 * Класс покупателя, наших покупателей будут обсулживать 2 кассы
 */
public class BuyerThread implements Runnable{
    // очередь касс
    private final BlockingQueue<Cashbox> cashboxes;

    public BuyerThread(BlockingQueue<Cashbox> cashboxes) {
        this.cashboxes = cashboxes;
    }

    @Override
    public void run() {
            try {
                /**
                 * метод take() удаляет и возвращает из головы очереди элемент и ожидает, если там же не оказалось элемента,
                 * т.е. до тех пор пока не окажется свободного элемента в нашей очереди, он ожидает,
                 * по сути делает сам и sync и wat в случае пустой коллекции
                 */
                Cashbox cashbox = cashboxes.take();
                System.out.println(Thread.currentThread().getName() + " обсулживается в кассе " + cashbox);
                Thread.sleep(5L);
                System.out.println(Thread.currentThread().getName() + " освобождаем кассу " + cashbox);
                cashboxes.add(cashbox);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }
}
