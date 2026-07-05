package org.example.multithreding.sync_collections.synchronized_collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListTest {
    public static void main(String[] args) throws InterruptedException {
//        List<Integer> integers = new ArrayList<>();
        List<Integer> integers = Collections.synchronizedList(new ArrayList<>());

        ListThread listThread1 = new ListThread(integers);
        ListThread listThread2 = new ListThread(integers);
        ListThread listThread3 = new ListThread(integers);
        ListThread listThread4 = new ListThread(integers);
        ListThread listThread5 = new ListThread(integers);
        ListThread listThread6 = new ListThread(integers);
        ListThread listThread7 = new ListThread(integers);

        listThread1.join();
        listThread2.join();
        listThread3.join();
        listThread4.join();
        listThread5.join();
        listThread6.join();
        listThread7.join();

        System.out.println(integers);
    }
}
