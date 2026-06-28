package org.example.multithreding.synchronizers.latch.black_friday;

import java.util.concurrent.CountDownLatch;

public class Friend extends Thread{
    private String name;
    private CountDownLatch countDownLatch;

    public Friend(String name, CountDownLatch countDownLatch) {
        this.name = name;
        this.countDownLatch = countDownLatch;
        start();
    }

    @Override
    public void run() {
        try {
            /**
             * Если счетчик countDownLatch > 0, то наш поток будет заблокирован до тех пор пока счетчик не станет равен 0
             * Если счетчик уже равен нулю, то наш поток бесприпятсвенно будет выполнять свою работу
             */
            countDownLatch.await();

            /**
             * после того, как счетчик станет равным нулю и поток выйдет из блокировки, будем выводить сообщение
             */
            System.out.println(name + "приступил к закупкам");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
