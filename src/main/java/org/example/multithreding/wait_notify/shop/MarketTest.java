package org.example.multithreding.wait_notify.shop;

public class MarketTest {
    public static void main(String[] args) {
        Market market = new Market();
        Thread producerThread = new Thread(new Producer(market));
        Thread consumerThread = new Thread(new Consumer(market));

        producerThread.start();
        consumerThread.start();
    }
}
