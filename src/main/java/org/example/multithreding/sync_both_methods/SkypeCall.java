package org.example.multithreding.sync_both_methods;

public class SkypeCall implements Runnable {
    private final Channel channel;

    public SkypeCall(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        channel.talk("Skype", 2);
    }
}
