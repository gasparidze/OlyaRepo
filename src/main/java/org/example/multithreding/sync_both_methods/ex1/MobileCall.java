package org.example.multithreding.sync_both_methods.ex1;

public class MobileCall implements Runnable {
    private final Channel channel;

    public MobileCall(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        channel.talk("Мобильная связь", 3);
    }
}
