package org.example.multithreding.thread_pool;

import java.util.Optional;
import java.util.Queue;

/**
 * Здесь представлен упрощенный варант того, как ThreadPool работает на самом деле
 */
public class ThreadPool extends Thread {
    private final Queue<Runnable> tasks;

    public ThreadPool(Queue<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while (true) {
            Optional<Runnable> task = Optional.empty();
            /**
             * здесь захватываем монитор всей очереди задач, поэтому нам надо побыстрее его освбододить
             * причем сам метод run() находится вне sync блока, т.е. непосредственно выполнение задач будет происходить
             * параллельно
             */
            synchronized (tasks){
                if (!tasks.isEmpty()) {
                    task = Optional.of(tasks.remove());
                }
            }
            task.ifPresent(Runnable::run);
        }
    }
}
