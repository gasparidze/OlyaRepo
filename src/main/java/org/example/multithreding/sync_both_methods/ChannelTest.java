package org.example.multithreding.sync_both_methods;

public class ChannelTest {
    public static void main(String[] args) throws InterruptedException {
        Channel channel = new Channel();
        Thread mobileThread = new Thread(new MobileCall(channel));
        Thread skypeThread = new Thread(new SkypeCall(channel));
        Thread whatsAppThread = new Thread(new WhatsAppCall(channel));

        mobileThread.start();
        skypeThread.start();
        whatsAppThread.start();

        mobileThread.join();
        skypeThread.join();
        whatsAppThread.join();

        System.out.println("Все разговоры по всем каналам закончены");
    }
}
