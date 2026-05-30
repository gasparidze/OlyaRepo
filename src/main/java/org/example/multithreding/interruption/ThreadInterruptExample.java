package org.example.multithreding.interruption;

public class ThreadInterruptExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Главный поток: Запускаем рабочего...");

        // Создаем рабочего, который выполняет долгую задачу
        Thread worker = new Thread(new HardWorker());
        worker.start();

        // Даем рабочему поработать 3 секунды
        Thread.sleep(7);

        System.out.println("Главный поток: Посылаем сигнал прерывания...");
        worker.interrupt();

        // Ждем пока рабочий завершится
        worker.join();
        System.out.println("Главный поток: Рабочий завершил работу");
    }
}
