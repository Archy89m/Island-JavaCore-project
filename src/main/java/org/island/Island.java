package org.island;

import statistics.StatisticsTask;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Island {

    private final int rows = 20;
    private final int cols = 100;
    private final Location[][] locations;
    private final ScheduledExecutorService statisticsScheduler;

    public Island() {
        System.out.println("Start game - " + LocalTime.now());
        this.locations = new Location[rows][cols];
        initializeLocations();
        System.out.println("Island created - " + LocalTime.now());
        System.out.println();
        this.statisticsScheduler = Executors.newScheduledThreadPool(1);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Location[][] getLocations() {
        return locations;
    }

    private void initializeLocations() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                locations[i][j] = new Location();
            }
        }
    }

    public Location getLocation(int row, int col) {
        return locations[row][col];
    }

    public void startSimulation() {
        StatisticsTask statisticsTask = new StatisticsTask(this);
        statisticsScheduler.scheduleAtFixedRate(statisticsTask, 1, 6, TimeUnit.SECONDS);
    }

    public void stopSimulation() {
        statisticsScheduler.shutdown();
    }


}
