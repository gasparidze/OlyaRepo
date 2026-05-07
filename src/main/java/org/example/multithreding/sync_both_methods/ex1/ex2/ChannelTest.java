package org.example.multithreding.sync_both_methods.ex1.ex2;

public class ChannelTest {
    public static void main(String[] args) {
        Thread mobileThread = new Thread(new Mobile());
        Thread skypeThread = new Thread(new Skype());
        Thread whatsAppThread = new Thread(new WhatsApp());

        mobileThread.start();
        skypeThread.start();
        whatsAppThread.start();
    }
}
