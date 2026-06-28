package org.example.multithreding.synchronizers.cyclic_barrier.rocket;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class RocketDetailRunnable implements Runnable {
    private final RocketDetail rocketDetail;
    private final CyclicBarrier cyclicBarrier;

    public RocketDetailRunnable(RocketDetail rocketDetail, CyclicBarrier cyclicBarrier) {
        this.rocketDetail = rocketDetail;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println("Деталь готовится: " + rocketDetail);
        try {
            Thread.sleep(1000);
            System.out.println("Деталь готова и ожидает: " + rocketDetail);
            /**
             * все потоки, которые вызвали await() ожидают до тех пор пока определенное количество потоков не вызовет этот метод
             * в нашем случае у нас 5 деталей, cyclicBarrier будет ожидать эти 5 деталей,
             * т.е. 5 раз должен кто-то вызвать метод await(), т.е. это будет 5 разных потоков => 5 разных деталей
             */
            cyclicBarrier.await();
            System.out.println("Деталь использована: " + rocketDetail);
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
