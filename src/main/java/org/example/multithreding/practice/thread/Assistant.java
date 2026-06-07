package org.example.multithreding.practice.thread;

import org.example.multithreding.practice.Dump;
import org.example.multithreding.practice.RobotDetail;
import org.example.multithreding.practice.Scientist;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Assistant extends Thread{
    private Dump dump;
    private Random random;
    private Scientist scientist;
    private Night night;

    public Assistant(Dump dump, Scientist scientist, Night night, String name) {
        this.dump = dump;
        this.scientist = scientist;
        this.night = night;
        this.random = new Random();
        setName(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                List<RobotDetail> details = getRobotDetailsFromDump();
                scientist.addDetails(details);
                waitNextNight();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private List<RobotDetail> getRobotDetailsFromDump() {
        List<RobotDetail> details = new ArrayList<>();
        synchronized (dump.getLock()) {
            int quantity = random.nextInt(4) + 1;
            for (int i = 0; i < quantity; i++) {
                RobotDetail detail = dump.takeDetail();
                if (detail != null) {
                    details.add(detail);
                }
            }
            System.out.println(getName() + " Взял " + details.size() + " деталей");
        }
        return details;
    }

    /**
     * Если ым хотим, чтобы для всех потоков ночь начиналось в однои то же время, то надо по нй и сихронизироваться всем потокам
     *
     * wait() всегда усупляет тот поток, который его вызвал, независимо от того, у какого объекта вызван wait()
     */
    private void waitNextNight() throws InterruptedException {
        synchronized (night.getLock()) {
            night.getLock().wait();
        }
    }
}
