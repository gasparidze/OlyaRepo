package org.example.multithreding.sync_both_methods.ex1;

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
}