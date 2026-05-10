package org.example.multithreding.wait_notify.shop.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Разберем на примере очереди в магазине, у нас будут кассы (ограниченные ресурс) и покупатеки (потоки),
 * сделаем пару касс и множество покупателей, таким образом у нас будет дефицит касс и покупатели будут ждать в очереди,
 * ожидая освобождения касс
 */
public class CashboxTest{
    public static void main(String[] args) {
        Queue<Cashbox> cashboxes = new ArrayDeque<>(List.of(new Cashbox(), new Cashbox()));


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
