package org.example.multithreding.sync_both_methods.ex1.ex2;

public class Channel {
    private static final Object LOCK = new Object();

    public void mobileCall() {
        synchronized (LOCK) {
            System.out.println("Mobile call starts");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Mobile call ends");
        }
    }

    public void skypeCall() {
        synchronized (LOCK) {
            System.out.println("Skype call starts");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Skype call ends");
        }
    }

    public void whatsAppCall() {
        synchronized (LOCK) {
            System.out.println("WhatsApp call starts");
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("WhatsApp call ends");
        }
    }
}

class Mobile implements Runnable{

    @Override
    public void run() {
        new Channel().mobileCall();
    }
}

class Skype implements Runnable{

    @Override
    public void run() {
        new Channel().skypeCall();
    }
}

class WhatsApp implements Runnable{

    @Override
    public void run() {
        new Channel().whatsAppCall();
    }
}
