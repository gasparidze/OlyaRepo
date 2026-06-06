package org.example.multithreding.practice;

import java.util.*;

public class Scientist {
    private Map<RobotDetail, Integer> details;

    public Scientist() {
        this.details = new EnumMap<>(RobotDetail.class);
    }

    public void addDetails(List<RobotDetail> details) {
        details.forEach(detail -> this.details.merge(detail, 1, Integer::sum));
        System.out.println(Thread.currentThread().getName() + ": Детали отданы ученому");
    }

    /**
     * HEAD - 5
     * LEFT_HAND - 4
     * CPU - 2
     * RAM - 1
     * values() - [5, 4, 2, 1]
     * min() - 1
     */
    public List<Robot> buildRobots() {
        List<Robot> robots = new ArrayList<>();
        if (!details.isEmpty()) {
            Optional<Integer> robotsCount = details.values().stream().min(Integer::compareTo);
            robotsCount.ifPresent(count -> {
                for (int i = 0; i < count; i++) {
                    details.forEach((k, v) -> details.merge(k, -1, Integer::sum));
                    robots.add(new Robot());
                }
            });
        }

        return robots;
    }
}
