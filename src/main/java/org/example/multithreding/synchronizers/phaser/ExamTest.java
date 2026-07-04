package org.example.multithreding.synchronizers.phaser;

import java.util.concurrent.Phaser;

public class ExamTest {
    public static void main(String[] args) {
        /**
         * Также здесь в конструкторе можно указать количество участников (потоков), которые должны выполнять все фазы действия.
         * Регистрация происходит внутри самого потока, когда он вызывает методы Phaser:
         * 1) register() - явная регистрация
         * 2) bulkRegister(int) - массовая регистрация
         * 3) arriveAndAwaitAdvance() - автоматическая регистрация при первом вызове
         * 4) arriveAndDeregister() - автоматическая регистрация при первом вызове
         *
         * Количество потоков, как и их назначение может быть любым.
         * Можно также добавлять потоки в любой фазе, просто тогда для перехода на следующую фазу, нужен будет +1 поток
         */
        Phaser phaser = new Phaser();
        new ExamThread("theory", phaser);
        new ExamThread("practice", phaser);
        new ExamThread("exam", phaser);

        // регистрируем поток main в phaser, чтобы он тоже учитывался при переходе на новую фазу
        phaser.register();

        int phase = phaser.getPhase();
        /**
         * поток main не может пройти дальше этой строчки пока все 3 потока не завершат свои действия, потому что все потоки
         * работают с одним и тем же объектом phaser
         */
        phaser.arriveAndAwaitAdvance();
        System.out.println("Фаза " + phase + " завершена\n");

        phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Фаза " + phase + " завершена\n");

        phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Фаза " + phase + " завершена\n");

        phaser.arriveAndDeregister();

        /**
         * Объект phaser завершает свою работу по завершению всех фаз
         * Для того, чтобы создать новуб логику работы фаз, нужно будет создавать новый объект Phaser
         */
        if (phaser.isTerminated()) {
            System.out.println("Phaser завершил свою работу");
        }
    }
}
