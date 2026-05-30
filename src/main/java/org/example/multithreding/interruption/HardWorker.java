package org.example.multithreding.interruption;

public class HardWorker implements Runnable{
    @Override
    public void run() {
        System.out.println("Рабочий: Начинаю сложную работу");

        // опишем долгий процесс, например, обработка 1 миллиона элементов
        for (int i = 1; i <= 1000000; i++) {
            // проверяем, хотят ли нас прервать
            if (Thread.currentThread().isInterrupted()){
                System.out.println("Рабочий: меня прерывают, Успел обработать " + i + " элементов");
                System.out.println("Рабочий: освобождаю ресурсы");
                // здесь можно закрыть файл, соединение с БД и т д, но мы просто поставим return
                return;
            }

            // имитация работы
            if (i % 100000 == 0) {
                System.out.println("Рабочий: Обработано " + i + " элементов");
            }
        }
        System.out.println("Рабочий: Вся работа закончена без прерываний");
    }
}
