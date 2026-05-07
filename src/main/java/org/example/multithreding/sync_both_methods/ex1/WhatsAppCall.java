package org.example.multithreding.sync_both_methods.ex1;

public class WhatsAppCall implements Runnable {
    private final Channel channel;

    public WhatsAppCall(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        channel.talk("WhatsApp", 4);
    }
}
