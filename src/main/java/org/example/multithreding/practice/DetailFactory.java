package org.example.multithreding.practice;

import java.util.Random;

public class DetailFactory {
    private static final Random random = new Random();
    private static final RobotDetail[] details = RobotDetail.values();

    public static RobotDetail getRandomDetail() {
        return details[random.nextInt(details.length)];
    }
}
