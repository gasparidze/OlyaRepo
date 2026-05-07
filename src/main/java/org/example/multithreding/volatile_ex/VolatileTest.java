package org.example.multithreding.volatile_ex;

public class VolatileTest {
    private static volatile boolean flag = false;

    /**
     *
     * thread1 -> flag = false -> крутится до тех пор пока не считает true из оперативки,
     * но когда он считает и когда второй поток решит из кеша переместить значение в main memory - неизвестно
     *
     * thread2 -> flag = false - > flag = true -> оперативку
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            while (!flag){
                System.out.println("still false");
            }
        });
        thread1.start();

        Thread.sleep(1000);

        Thread thread2 = new Thread(() -> {
            flag = true;
            System.out.println("flag is set");
        });
        thread2.start();
    }
}
