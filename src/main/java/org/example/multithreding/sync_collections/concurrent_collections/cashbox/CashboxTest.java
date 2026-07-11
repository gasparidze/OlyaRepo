package org.example.multithreding.sync_collections.concurrent_collections.cashbox;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Разберем на примере очереди в магазине, у нас будут кассы (ограниченные ресурс) и покупатеки (потоки),
 * сделаем пару касс и множество покупателей, таким образом у нас будет дефицит касс и покупатели будут ждать в очереди,
 * ожидая освобождения касс
 */
public class CashboxTest{
    public static void main(String[] args) {
        BlockingQueue<Cashbox> cashboxes = new ArrayBlockingQueue<>(2, true, List.of(new Cashbox(), new Cashbox()));

        new Thread(new BuyerThread(cashboxes)).start();
        new Thread(new BuyerThread(cashboxes)).start();
        new Thread(new BuyerThread(cashboxes)).start();
        new Thread(new BuyerThread(cashboxes)).start();
        new Thread(new BuyerThread(cashboxes)).start();
        new Thread(new BuyerThread(cashboxes)).start();
        new Thread(new BuyerThread(cashboxes)).start();
        new Thread(new BuyerThread(cashboxes)).start();
    }
}
