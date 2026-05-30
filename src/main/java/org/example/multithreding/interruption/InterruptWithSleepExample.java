package org.example.multithreding.interruption;

public class InterruptWithSleepExample {
    public static void main(String[] args) throws InterruptedException {
        Thread sleepyWorker = new Thread(() -> {
            try {
                System.out.println("Спяший рабочий: сплю 10 секунд...");
                Thread.sleep(10000);
                System.out.println("Спящий рабочий: Проснулся сам");
            } catch (InterruptedException e) {
                System.out.println("Спящий рабочий: Меня разбудули прерыванием");
                System.out.println("Спящий рабочий: Завершаю работу досрочно");
            }
        });

        sleepyWorker.start();

        Thread.sleep(2000);
        System.out.println("Главный поток проснулся");
        System.out.println("Главный поток: Бужу спящего рабочего...");
        sleepyWorker.interrupt();

        sleepyWorker.join();
        System.out.println("Разбудили");
    }
}
