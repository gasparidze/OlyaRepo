package org.example.multithreding.deadlock;

/**
 * Разберем deadlock на примере: у нас будет 2 аккаунта (счет) и мы будем перечислять деньги с одного счета на другой
 */
public class Account {
    private static int generator = 1;
    private int id;
    private int money;

    public Account(int money) {
        id = generator++;
        this.money = money;
    }

    public void add(int money) {
        this.money += money;
    }

    public boolean takeOff(int money) {
        if (this.money >= money) {
            this.money -= money;
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", money=" + money +
                '}';
    }
}
