package org.example.multithreding.practice;

import org.example.multithreding.practice.thread.Assistant;
import org.example.multithreding.practice.thread.Factory;
import org.example.multithreding.practice.thread.Night;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Dump dump = new Dump();
        Night night = new Night();
        Factory factory = new Factory(dump, night, "Factory");

        Scientist scientist1 = new Scientist();
        Scientist scientist2 = new Scientist();
        Assistant assistant1 = new Assistant(dump, scientist1, night, "Assistant-1");
        Assistant assistant2 = new Assistant(dump, scientist2, night,"Assistant-2");

        night.start();
        factory.start();
        assistant1.start();
        assistant2.start();

        night.join();
        factory.join();
        assistant1.join();
        assistant2.join();

        System.out.println("Количество собранных роботов первым ученым: " + scientist1.buildRobots().size());
        System.out.println("Количество собранных роботов вторым ученым: " + scientist2.buildRobots().size());
    }
}
