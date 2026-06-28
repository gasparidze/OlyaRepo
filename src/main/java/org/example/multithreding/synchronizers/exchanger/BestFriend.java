package org.example.multithreding.synchronizers.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Допустим, что играем 3 раза
 */
public class BestFriend extends Thread{
    private String name;
    private List<Action> myActions;
    private Exchanger<Action> exchanger;

    public BestFriend(String name,List<Action> myActions, Exchanger<Action> exchanger) {
        this.name = name;
        this.exchanger = exchanger;
        this.myActions = myActions;
        start();
    }

    /**
     * метод exchange() возвращает нам информацию, полученную от второго потока
     * на вход отдаем свлю информацию, на выход получаем информацию второго потока
     * если друг пока не готов отослать ответ, то на вызове метода exchange() поток заблокируется
     * пока друг не вызовет свой exchange, аднный поток будет заблокирован
     */
    @Override
    public void run() {
        Action reply;
        for (Action action : myActions) {
            try {
                reply = exchanger.exchange(action);
                whoWins(action, reply);
                sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void whoWins(Action myAction, Action friendsAction) {
        if ((myAction == Action.ROCK && friendsAction == Action.SCISSORS) ||
                (myAction == Action.SCISSORS && friendsAction == Action.PAPER) ||
                (myAction == Action.PAPER && friendsAction == Action.ROCK)
        ) {
            System.out.println(name + " WINS!!!");
        }
    }
}
