package org.example.multithreding.synchronizers.exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class RockPaperScissors {
    public static void main(String[] args) {
        Exchanger<Action> exchanger = new Exchanger<>();

        List<Action> firstFriendActions = new ArrayList<>();
        firstFriendActions.add(Action.SCISSORS);
        firstFriendActions.add(Action.PAPER);
        firstFriendActions.add(Action.SCISSORS);

        List<Action> secondFriendActions = new ArrayList<>();
        secondFriendActions.add(Action.PAPER);
        secondFriendActions.add(Action.ROCK);
        secondFriendActions.add(Action.ROCK);

        new BestFriend("FirstFriend", firstFriendActions, exchanger);
        new BestFriend("SecondFriend", secondFriendActions, exchanger);
    }
}
