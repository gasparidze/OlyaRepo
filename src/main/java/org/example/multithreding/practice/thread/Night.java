package org.example.multithreding.practice.thread;

public class Night extends Thread {
    private final Object lock = new Object();

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            /**
             * Night:   [---Будим---] [---Спим 100 мс...] [---Будим---] [---Спим 100 мс...]
             * Factory: [---Ждут/Спят---]  [...работают...] [---Ждут/Спят---] [...работают/Спят...]
             * Assistant: [---Ждут/Спят---]  [...работают...] [---Ждут/Спят---] [...работают/Спят...]
             */

            /**
             * Ночь 1:
             * Night входит в sync блок, вызывает notifyAll, отрабатывает в холостую, т.к. ни один поток еще не ждет
             * Night вызызвает lock.wait(100L); - засывает и освобождает монитор lock
             * Factory и Assistant (ждущие на night.getLock().wait()) просываются и начинают работу
             * Factory выбрасывает детали, Assistant забирает детали (параллельно)
             * Через 100мс Night просыпается и начинает следующую итерацию
             *
             * Ночь 2:
             * Night снова вызывает notifyAll() - будит Factory и Assistant
             * И так далее
             *
             * Factory и Assistant ждут пробуждения от Night
             *
             * Ожидание в 100 мс
             * Factory и Assistant выполнят свою работу каждый за 5мс = 10мс,
             * т.е. 90 мс мы будем ждать пока проснется поток Night и с помощью notifyAll разбудит спящие потоки
             *
             */
            synchronized (lock) {
                try {
                    System.out.printf("-------------\nNight %s started\n", (i + 1));
                    lock.notifyAll();
                    lock.wait(100L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        /**
         * Чтобы разбудить Factory и Assistant, которые ждут 101-ю ночь, и дать им корректно завершиться
         */
        synchronized (lock){
            lock.notifyAll();
        }
    }

    public Object getLock() {
        return lock;
    }
}
