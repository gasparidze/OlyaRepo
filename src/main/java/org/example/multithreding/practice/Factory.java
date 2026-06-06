package org.example.multithreding.practice;

import java.util.Random;

public class Factory extends Thread{
    private Dump dump;
    private Night night;
    private Random random;

    public Factory(Dump dump, Night night, String name) {
        this.dump = dump;
        this.night = night;
        this.random = new Random();
        setName(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                throwDetailsToFactory();
                waitNextNight();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void throwDetailsToFactory() {
        int quantity = random.nextInt(4) + 1;
        for (int i = 0; i < quantity; i++) {
            dump.addDetail(DetailFactory.getRandomDetail());
        }
        System.out.println(getName() + " Добавил " + quantity + " деталей");
    }

    private void waitNextNight() throws InterruptedException {
        synchronized (night.getLock()) {
            night.getLock().wait();
        }
    }
}
