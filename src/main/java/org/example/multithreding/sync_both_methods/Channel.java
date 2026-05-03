package org.example.multithreding.sync_both_methods;

import java.time.LocalTime;

public class Channel {
    /**
     * Общий монитор для всех каналов связи
     * Все потоки будут синхронизированы на одном объекта, поэтому одновременно занят может быть только один канал
     * Принято использовать объект Object, потому что Object - родительский класс для всех других классов
     * Мониторы у разных классов - одинаковые, неважно монитор объекта какого класса мы используем, но принят объекта Object'а
     */
    private static final Object LOCK = new Object();

    /**
     * Метод для разговора по любому каналу связи.
     * @param channelName название канала (моб.связь, Skype, WhatsApp)
     * @param durationSeconds длительность разговора в секундах
     */
    public void talk(String channelName, int durationSeconds) {
        synchronized (LOCK) {
            System.out.println(String.format("[%s] %s: начал разговор на %d секунд", getCurrentTime(), channelName, durationSeconds));

            try {
                // Имитируем разговор
                Thread.sleep(durationSeconds * 1000L);
            } catch (InterruptedException e) {
                System.out.println(String.format("[%s] %s: разговор прерван",
                        getCurrentTime(), channelName));
                Thread.currentThread().interrupt();
            }
            System.out.println(String.format("[%s] %s: закончил разговор", getCurrentTime(), channelName));
        }
    }

    private String getCurrentTime() {
        return LocalTime.now().toString();
    }

    static class MobileCall implements Runnable {
        private final Channel channel;

        public MobileCall(Channel channel) {
            this.channel = channel;
        }

        @Override
        public void run() {
            channel.talk("Мобильная связь", 3);
        }
    }

    static class SkypeCall implements Runnable {
        private final Channel channel;

        public SkypeCall(Channel channel) {
            this.channel = channel;
        }

        @Override
        public void run() {
            channel.talk("Skype", 2);
        }
    }

    static class WhatsAppCall implements Runnable {
        private final Channel channel;

        public WhatsAppCall(Channel channel) {
            this.channel = channel;
        }

        @Override
        public void run() {
            channel.talk("WhatsApp", 4);
        }
    }
}
