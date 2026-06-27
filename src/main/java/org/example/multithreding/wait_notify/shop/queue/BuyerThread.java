package org.example.multithreding.wait_notify.shop.queue;

import java.util.Queue;

/**
 * Класс покупателя, наших покупателей будут обсулживать 2 кассы
 */
public class BuyerThread implements Runnable{
    // очередь касс
    private final Queue<Cashbox> cashboxes;

    public BuyerThread(Queue<Cashbox> cashboxes) {
        this.cashboxes = cashboxes;
    }

    @Override
    public void run() {
            try {
                synchronized (cashboxes) {
                    /**
                     * делаем все в цикле, т.к. если мы ожидаем свободную кассу, то мы должны заново првоерить очередь на доступность касс
                     */
                    while (true) {
                        /**
                         * Без блока synchronized здесь может произойти некорректное удаление кассы из очереди,
                         * потому что мы не захватыавеем нигде монитор у наших касс (cashboxes)
                         * => в один и тот же момент времени два потока могут увидеть, что в cashboxes есть свободные кассы
                         * и оба сделают remove, естественно один из потоков не получит cashboxes (т.к. забрал другой поток и пока не отпустил)
                         * и remove в таком случае сгенерирует NoSuchElementException
                         */
                        if (!cashboxes.isEmpty()) {
                            Cashbox cashbox = cashboxes.remove();// забираем кассы из очереди
                            System.out.println(Thread.currentThread().getName() + " обслуживается в кассе " + cashbox);

                            /**
                             * Пока поток засыпает на определенное время, он все также занимает монитор (sleep), а это не совсем правильно,
                             * т.к. мы захватываем монитор всех касс, а используем только одну кассу.
                             * Таким образом, мы должны освободить монитор всех наших касс, пока мы обслуживаемся только в одном кассе.
                             * Для этого есть аналог sleep - это метод wait, отличие лишь в том, что этот метод мы должны вызвать у объекта,
                             * монитор которого мы захватываем.
                             * При вызове метода wait мы освобождаем монитор наших касс (cashboxes) => другие потоки могут захватить монитор,
                             * но только во время нашего ожидания на 5мс и после того, как мы подождали 5мс, мы опять должны захватить монитор,
                             * потому что мы все еще находимся в нашем sync блоке, т.е. 5мс - это максимальное кол-во времени,
                             * которое мы должны ждать перед тем, как прождолжить выполнять наш код.
                             */
//                            Thread.sleep(5L);
                            cashboxes.wait(5L);

                            System.out.println(Thread.currentThread().getName() + " освобождаем кассу " + cashbox);
                            cashboxes.add(cashbox);
                            cashboxes.notifyAll();
                            break;
                        } else {
                            // если в текущий момент времени свободных касс нетт
                            System.out.println(Thread.currentThread().getName() + " ожидает свободную кассу");
//                            Thread.sleep(5L);
                            /**
                             * Здесь не указываем время, потому что покупатель не знает, как долго ему придется ждать.
                             * Ждать будем до тех пор, пока друой поток не вызовет у того же самого объекта,
                             * у которого вызвали wait, методы notify/notifyAll
                             */
                            cashboxes.wait();
                        }
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }
}
