package org.island;

import entity.Animal;
import executors.ScheduledExecutor;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Island {

    private final int rows = 2;
    private final int cols = 2;
    private final Location[][] locations;
    private ScheduledExecutor scheduledExecutor = null;
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
                locations[i][j] = new Location(this, i, j);
            }
        }
    }

    public Location getLocation(int row, int col) {
        synchronized (locations) {
            return locations[row][col];
        }
    }

    public void startSimulation() {

        scheduledExecutor = new ScheduledExecutor(this);
        scheduledExecutor.startBirthTask();
        scheduledExecutor.startClearingTask();
        scheduledExecutor.startStatisticsTask();
    }

    public void stopSimulation() {
        scheduledExecutor.stopTasks();
    }

    public void moveAnimal(Animal animal, int curI, int curJ, int newI, int newJ) {
        synchronized(locations[curI][curJ]) {
            synchronized(locations[newI][newJ]) {
                locations[curI][curJ].removeEntity(animal);
                locations[newI][newJ].addEntity(animal);
                animal.setLocation(locations[newI][newJ]);
            }
        }
    }
}
