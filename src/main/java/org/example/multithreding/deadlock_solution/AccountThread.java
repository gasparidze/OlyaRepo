package org.example.multithreding.deadlock_solution;

public class AccountThread extends Thread {
    private final Account accountFrom;
    private final Account accountTo;

    public AccountThread(Account accountFrom, Account accountTo){
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2000; i++) {
            /**
             * Если получилось заблокировать все счета, то производим операцию, т.е. переводим деньги
             */
            lockAccounts();
            try {
                if (accountFrom.takeOff(10)) {
                    accountTo.add(10);
                }
            } finally {
                /**
                 * Если раньше, монитор освобождался автоматически при выходе из блока sync, то сейчас, ипользуя ReentrantLock,
                 * мы должны это делать вручную с помощью метода unlock()
                 * После того как мы выполнили перевод, мы должны оптустить мониторы наших счетов, сделать это можно в finally
                 * Если не использовать unlock в finally в методе takeOff и, например, выброисится exception, то мониторы будут захвачены на неопределенное время
                 */
                accountFrom.getLock().unlock();
                accountTo.getLock().unlock();
            }
        }
    }

    /**
     * До тех пор пока мы не захватим монитор у обоих счетов, деньги перевсте не получится
     * Теперь мы можем получать у каждого объекта свой lock
     * Мы можем пользоваться следующими методами:
     * 1) lock() - захватывает монитор объекта
     * 2) tryLock() - возвращает boolean, получилось ли захватить монитор или нет
     * 3) unlock() - отпускает монитор, если он захвачен
     *
     * Отличие методов lock() и tryLock() в том, что lock() ждет до тех пор пока не получится захватить монитор объекта
     * tryLock - наоборот, пытается захватить монитор и если не получилось, возвращает false и не блокирует текущий поток
     *
     * В нашем случае мы должны использовать tryLock у 2х наших счетов для того, чтобы попытаться захватить монитор
     * Если же не получилось захватить монитор у какого-либо счета, то мы должны его отпустить.
     * Нам ни в коем случае нельзя ждать освобождение второго счета, если мы захватили, к примеру, первый,
     * иначе мы можем попасть опять в ситуацию с deadlock.
     * Чтобы избежать deadlock мы должны быть уверены, что один поток захватил сразу оба монитор, в противном случае
     * отпустил все захваченные мониторы. Если не сделать unlock, т.е. не отпустить монитор, будет также deadlock.
     * Либо захватываем оба, либо не захватываем ничего
     */
    private void lockAccounts() {
        while (true) {
            boolean fromLockResult = accountFrom.getLock().tryLock();
            boolean toLockResult = accountTo.getLock().tryLock();
            if (fromLockResult && toLockResult){
                break; // если получилось захватить у обоих, то просто выходим из цикла
            }
            /**
             * В противном случае, либо оба false либо какой-то один из них false,
             * т.е. если какой-то из мониторов не оплучилось захватить => мы должны их отпустить,
             * чтобы не получилась ситуация с deadlock
             *
             * Делаем unlock() тех объектов, монитор которыз получилось захватить
             * Мы не можем вызвать unlock() у lock, если мы не захватили его монитор, иначе выпадет exception
             * => всегда нужно использовать if
             */
            if (fromLockResult){
                accountFrom.getLock().unlock();
            }
            if (toLockResult){
                accountTo.getLock().unlock();
            }
        }
    }
}
