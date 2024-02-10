package org.island;

import executors.EntityActionExecutor;
import executors.ScheduledExecutor;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Island {

    private final int rows = 20;
    private final int cols = 20;
    private final Location[][] locations;
    private ScheduledExecutor scheduledExecutor = null;
    private EntityActionExecutor entityActionExecutor = null;
    private static final AtomicInteger numberOfBorn = new AtomicInteger();
    private static final AtomicInteger numberOfDeaths = new AtomicInteger();

    public Island() {
        this.locations = new Location[rows][cols];
        initializeLocations();
        System.out.println("Island created - " + LocalTime.now());
        System.out.println();
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public static void increaseNumberOfBorn() {
        numberOfBorn.getAndIncrement();
    }

    public static void increaseNumberOfDeaths() {
        numberOfDeaths.getAndIncrement();
    }

    public static int getBorn() {
        return numberOfBorn.get();
    }

    public static int getDead() {
        return numberOfDeaths.get();
    }

    public Location[][] getLocations() {
        return locations;
    }

    private void initializeLocations() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                locations[i][j] = new Location(this);
            }
        }
    }

    public Location getLocation(int row, int col) {
        return locations[row][col];
    }

    public void startSimulation() {

        scheduledExecutor = new ScheduledExecutor(this);
        scheduledExecutor.startBirthTask();
        scheduledExecutor.startClearingTask();
        scheduledExecutor.startStatisticsTask();

        entityActionExecutor = new EntityActionExecutor(this);
        entityActionExecutor.startGrowingTask();
    }

    public void stopSimulation() {
        scheduledExecutor.stopTasks();
        entityActionExecutor.stopEntityTaskAction();
    }
}
