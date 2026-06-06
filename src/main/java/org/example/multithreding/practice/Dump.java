package org.example.multithreding.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dump {
    private List<RobotDetail> details;
    private Random random;

    public Dump() {
        random = new Random();
        this.details = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            details.add(DetailFactory.getRandomDetail());
        }
    }

    public void addDetail(RobotDetail robotDetail) {
        details.add(robotDetail);
    }

    public RobotDetail takeDetail(){
        return !details.isEmpty() ? details.remove(random.nextInt(details.size())) : null;
    }
}
