package org.example.multithreding.synchronizers.phaser;

import java.util.concurrent.Phaser;

public class ExamThread extends Thread {
    private Phaser phaser;
    private String name;

    public ExamThread(String name, Phaser phaser) {
        this.name = name;
        this.phaser = phaser;

        /**
         * Для того, чтобы поток принимал участие в работе нашего phaser (этапщик), его нужно зарегистрировать,
         * т.е. теперь при создаини потока ExamThread объект phaser будет знать о зарегистрированных потоках
         * и будет понимать, сколько потоков в нем участвует и сколько потоков должны дойти до конца определенной фазы,
         * чтобы можно было начинать следующую фазу
         */
        this.phaser.register();
        start();
    }

    /**
     * Количество фаз обычно проектируется заранее, в данном примере мы сделаем 3 фазы
     */
    @Override
    public void run() {
        /**
         * У phaser стоит счетчик
         * все зарегистрированные потоки должны сообщить phaser, что они завершили определенную фазу и что фазу можно менять
         * и пока все зарегистрированные потоки не сообщат, что они фазу завершили, фаза переключена не будет
         * как только все сообщили, phaser переключается с одной фазы на другую
         */
        System.out.println(name + " выполняет фазу: " + phaser.getPhase());
        /**
         * Как сообщить о том, что поток завершил фазу и он готов двигаться дальше?
         * Существует для этого несколько методов:
         * 1) arriveAndAwaitAdvance() - это означает, что мы прибываем к нашему барьеру/защелки/в конец фазы
         * и ожидаем на этом барьере пока все остальные потоки не закончат эту фазу, т.к. поток переходит в состояние BLOCKED
         * и ждет пока phaser не начнет следующую фазу.
         * Метод arriveAndAwaitAdvance позволяет мне использовать phaser как CyclicBarrier
         *
         * 2) arrive() - позволяет сказать phaser, что данный поток фазу завершил и он идет дальше, т.е. phaser у себя
         * отщелкнул (как бы сделал countDown) данный поток от тех, которые ему должны сообщить о завершении,
         * но поток при этом пошел дальше
         * Метод arrive позволяет мне использовать phaser как CountDownLatch
         *
         * 3) awaitAdvance(int phase) - позволяет подождать опеределенную фазу
         * если phaser.getPhase() = phase, то метод ждет, если нет, то не ждет
         *
         * Также есть и другие методы, например,
         * getPhase() - позволяет узнать текущую фазу
         * getArrivedParties() - позволяет узнать, сколько конкретно потоков уже завершило конкретную фазу
         * getUnarrivedParties() - позволяет узнать, сколько потоков мы еще ждем, чтобы фазу переключить
         */
        phaser.arriveAndAwaitAdvance(); // фаза 0

        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(name + " выполняет фазу: " + phaser.getPhase());
        phaser.arriveAndAwaitAdvance(); // фаза 1

        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(name + " выполняет фазу: " + phaser.getPhase());
        /**
         * поток прибывает в конец фазы и дерегистрирует/выбывает из участия в процессе объекта phaser,
         * т.е. данный поток больше не требуется для перехода на следующую фазу,
         * счетчик количества потоков у phaser уменьшается на единицу
         */
        phaser.arriveAndDeregister(); // фаза 2
    }
}
