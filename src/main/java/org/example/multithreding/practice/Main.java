package org.example.multithreding.practice;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Dump dump = new Dump();
        Night night = new Night();
        Factory factory = new Factory(dump, night, "Factory");

        Scientist scientist1 = new Scientist();
        Scientist scientist2 = new Scientist();
        Assistant assistant1 = new Assistant(dump, scientist1, night, "Assistant-1");
        Assistant assistant2 = new Assistant(dump, scientist1, night,"Assistant-2");

        factory.start();
        assistant1.start();
        assistant2.start();

        factory.join();
        assistant1.join();
        assistant2.join();

        System.out.println("Количество собранных роботов первым ученым: " + scientist1.buildRobots().size());
        System.out.println("Количество собранных роботов вторым ученым: " + scientist2.buildRobots().size());
    }
}
