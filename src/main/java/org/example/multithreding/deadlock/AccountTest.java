package org.example.multithreding.deadlock;

public class AccountTest {
    public static void main(String[] args) throws InterruptedException {
        Account account1 = new Account(20000);
        Account account2 = new Account(20000);

        /**
         * accountThread1:
         * accountFrom: account1 +
         * accountTo: account2
         *
         * accountThread2:
         * accountFrom: account2 +
         * accountTo: account1
         */
        AccountThread accountThread1 = new AccountThread(account1, account2);
        AccountThread accountThread2 = new AccountThread(account2, account1);

        accountThread1.start();
        accountThread2.start();

        accountThread1.join();
        accountThread2.join();

        System.out.println(account1);
        System.out.println(account2);
    }
}
