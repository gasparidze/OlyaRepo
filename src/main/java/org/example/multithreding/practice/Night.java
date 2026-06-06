package org.example.multithreding.practice;

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
    }

    public Object getLock() {
        return lock;
    }
}
